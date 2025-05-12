package exercicio04.exerciciosalas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NomeResponsavel;
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Long getId() {
        return id;
    }

    public String getNomeResponsavel() {
        return NomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        NomeResponsavel = nomeResponsavel;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }


    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
