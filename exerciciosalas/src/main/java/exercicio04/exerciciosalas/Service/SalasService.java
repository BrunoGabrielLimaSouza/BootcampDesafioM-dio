package exercicio04.exerciciosalas.Service;


import exercicio04.exerciciosalas.Entity.Salas;
import exercicio04.exerciciosalas.Repository.ReservaRepository;
import exercicio04.exerciciosalas.Repository.SalasRepository;
import org.springframework.stereotype.Service;
import

@Service
public class SalasService {
    private final SalasRepository salasRepository;
    private final ReservaRepository reservaRepository;


    public SalasService(SalasRepository salasRepository){
        this.salasRepository = salasRepository;

    }

    public Salas criarSalas(Salas salas) {
        validarSala(salas);
        return salasRepository.save(salas);
    }

    public void validarSala(Salas salas){
        if (salaRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }
    }
}
