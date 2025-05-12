package exercicio04.exerciciosalas.Entity.DTO;

import exercicio04.exerciciosalas.Entity.Reserva;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservaDTO {

    private Long id;

    @NotBlank
    private String nomeResponsavel;

    @NotNull
    @Future(message = "O início da reserva deve estar no futuro")
    private LocalDateTime inicio;

    @NotNull
    @Future(message = "O fim da reserva deve estar no futuro")
    private LocalDateTime fim;

    @NotNull
    private Long salaId;

    public ReservaDTO() {
    }

    public ReservaDTO(Reserva reserva) {
        this.nomeResponsavel = reserva.getNomeResponsavel();
        this.inicio = reserva.getInicio();
        this.fim = reserva.getFim();
        this.salaId = reserva.getSala().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
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

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }
}
