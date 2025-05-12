package exercicio04.exerciciosalas.Service;

import exercicio04.exerciciosalas.Entity.DTO.ReservaDTO;
import exercicio04.exerciciosalas.Entity.DTO.SalaDTO;
import exercicio04.exerciciosalas.Entity.Reserva;
import exercicio04.exerciciosalas.Entity.Sala;
import exercicio04.exerciciosalas.Repository.ReservaRepository;
import exercicio04.exerciciosalas.Repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }


    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setNomeResponsavel(reservaDTO.getNomeResponsavel());
        reserva.setInicio(reservaDTO.getInicio());
        reserva.setFim(reservaDTO.getFim());
        validarReserva(reserva.getNome());
        reserva = reservaRepository.save(reserva);
        return new reservaDTO(reserva);

    }


    private void validarReserva(String nome) {
        if (salaRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }
    }

    public List<ReservaDTO> listarSalas() {

        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaDTO::new).toList();
    }


    public void removerReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva com ID " + id + " não encontrada.");
        }
        reservaRepository.deleteById(id);
    }

    public ReservaDTO atualizarSala(Long id, ReservaDTO reservaDTO) {
        Reserva reservaExistente = reservaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva com ID " + id + " não encontrada."));

        reservaExistente.setNomeResponsavel(reservaDTO.getNomeResponsavel());
        reservaExistente.setInicio(reservaDTO.getInicio());
        reservaExistente.setFim(reservaDTO.getFim());
        validarSala(reservaExistente.getNome());
        reservaExistente = reservaRepository.save(reservaExistente);
        return new ReservaDTO(reservaExistente);
    }


}
