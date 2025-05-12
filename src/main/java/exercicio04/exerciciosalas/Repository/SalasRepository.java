package exercicio04.exerciciosalas.Repository;

import exercicio04.exerciciosalas.Entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalasRepository extends JpaRepository<Sala, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
