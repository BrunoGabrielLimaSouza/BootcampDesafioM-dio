package exercicio04.exerciciosalas.Service;

import exercicio04.exerciciosalas.Entity.Sala;
import exercicio04.exerciciosalas.Repository.SalasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalasService {

    private final SalasRepository salasRepository;

    @Autowired
    public SalasService(SalasRepository salasRepository) {
        this.salasRepository = salasRepository;
    }


    public Sala criarSala(Sala sala) {
        validarSala(sala.getNome());
        return salasRepository.save(sala);
    }


    private void validarSala(String nome) {
        if (salasRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }
    }

    public List<Sala> listarSalas() {
        return salasRepository.findAll();
    }

    public Sala buscarPorId(Long id) {
        return salasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala com ID " + id + " não encontrada."));
    }

    public void removerSala(Long id) {
        if (!salasRepository.existsById(id)) {
            throw new EntityNotFoundException("Sala com ID " + id + " não encontrada.");
        }
        salasRepository.deleteById(id);
    }

    public Sala atualizarSala(Long id, Sala novaSala) {
        Sala salaExistente = buscarPorId(id);

        if (!salaExistente.getNome().equalsIgnoreCase(novaSala.getNome()) &&
                salasRepository.existsByNomeIgnoreCase(novaSala.getNome())) {
            throw new IllegalArgumentException("Já existe outra sala com esse nome.");
        }

        salaExistente.setNome(novaSala.getNome());
        salaExistente.setCapacidadeMaxima(novaSala.getCapacidadeMaxima());
        salaExistente.setLocalizacao(novaSala.getLocalizacao());

        return salasRepository.save(salaExistente);
    }
}
