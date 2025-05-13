package exercicio04.exerciciosalas.Service;

import exercicio04.exerciciosalas.Entity.DTO.ReservaDTO;
import exercicio04.exerciciosalas.Entity.Reserva;
import exercicio04.exerciciosalas.Entity.Sala;
import exercicio04.exerciciosalas.Repository.ReservaRepository;
import exercicio04.exerciciosalas.Repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, SalaRepository salaRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
    }

    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala com ID " + reservaDTO.getSalaId() + " não encontrada."));

        Reserva reserva = new Reserva();
        reserva.setNomeResponsavel(reservaDTO.getNomeResponsavel());
        reserva.setInicio(reservaDTO.getInicio());
        reserva.setFim(reservaDTO.getFim());
        reserva.setSala(sala);

        validarReserva(reserva);
        reserva = reservaRepository.save(reserva);
        return new ReservaDTO(reserva);
    }

    public void validarReserva(Reserva reserva) {
        if (reserva.getInicio().isAfter(reserva.getFim()) || reserva.getInicio().isEqual(reserva.getFim())) {
            throw new IllegalArgumentException("A hora de início deve ser antes da hora de fim.");
        }

        if (reserva.getInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível reservar para o passado.");
        }

        List<Reserva> reservasExistentes = reservaRepository
                .findBySalaIdAndInicioLessThanAndFimGreaterThan(
                        reserva.getSala().getId(),
                        reserva.getFim(),
                        reserva.getInicio()
                );

        if (!reservasExistentes.isEmpty()) {
            throw new IllegalArgumentException("Já existe uma reserva sobreposta para essa sala.");
        }
    }

    public List<ReservaDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    public List<ReservaDTO> listarReservasPorSala(Long salaId) {
        List<Reserva> reservas = reservaRepository.findBySalaId(salaId);
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    public void cancelarReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva com ID " + id + " não encontrada.");
        }
        reservaRepository.deleteById(id);
    }

    public ReservaDTO atualizarReserva(Long id, ReservaDTO dto) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva com ID " + id + " não encontrada."));

        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala com ID " + dto.getSalaId() + " não encontrada."));

        reservaExistente.setNomeResponsavel(dto.getNomeResponsavel());
        reservaExistente.setInicio(dto.getInicio());
        reservaExistente.setFim(dto.getFim());
        reservaExistente.setSala(sala);

        validarReserva(reservaExistente);
        reservaExistente = reservaRepository.save(reservaExistente);
        return new ReservaDTO(reservaExistente);
    }
}
