package exercicio04.exerciciosalas.Service;

import exercicio04.exerciciosalas.Entity.DTO.SalaDTO;
import exercicio04.exerciciosalas.Entity.Sala;
import exercicio04.exerciciosalas.Repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    @Autowired
    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }


    public SalaDTO criarSala(SalaDTO salaDTO) {
        Sala sala = new Sala();
        sala.setNome(salaDTO.getNome());
        sala.setCapacidadeMaxima(salaDTO.getCapacidadeMaxima());
        sala.setLocalizacao(salaDTO.getLocalizacao());
        validarSala(sala.getNome());
        sala = salaRepository.save(sala);
        return new SalaDTO(sala);

    }


    private void validarSala(String nome) {
        if (salaRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }
    }

    public List<SalaDTO> listarSalas() {

        List<Sala> salas = salaRepository.findAll();
        return salas.stream().map(SalaDTO::new).toList();
    }

    public SalaDTO buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala com ID " + id + " não encontrada."));
        return new SalaDTO(sala);

    }

    public void removerSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new EntityNotFoundException("Sala com ID " + id + " não encontrada.");
        }
        salaRepository.deleteById(id);
    }

    public SalaDTO atualizarSala(Long id, SalaDTO salaDTO) {
        Sala salaExistente = salaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala com ID " + id + " não encontrada."));

        salaExistente.setNome(salaDTO.getNome());
        salaExistente.setCapacidadeMaxima(salaDTO.getCapacidadeMaxima());
        salaExistente.setLocalizacao(salaDTO.getLocalizacao());
        validarSala(salaExistente.getNome());
        salaExistente = salaRepository.save(salaExistente);
        return new SalaDTO(salaExistente);
    }


}
