package exercicio04.exerciciosalas.Repository;

import exercicio04.exerciciosalas.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface   ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findBySalaId(Long salaId);
    List<Reserva> findBySalaIdAndInicioLessThanAndFimGreaterThan(Long salaId, LocalDateTime fim, LocalDateTime inicio);
}
