package exercicio04.exerciciosalas.Repository;

import exercicio04.exerciciosalas.Entity.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reservas, Long> {
}
