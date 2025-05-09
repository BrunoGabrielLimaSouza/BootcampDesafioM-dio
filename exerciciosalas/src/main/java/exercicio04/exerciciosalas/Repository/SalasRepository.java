package exercicio04.exerciciosalas.Repository;

import exercicio04.exerciciosalas.Entity.Salas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalasRepository extends JpaRepository<Salas, Long> {
}
