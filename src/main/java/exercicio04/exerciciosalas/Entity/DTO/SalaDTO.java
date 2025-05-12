package exercicio04.exerciciosalas.Entity.DTO;

import exercicio04.exerciciosalas.Entity.Sala;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SalaDTO {

    private  Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Integer capacidadeMaxima;

    private String localizacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {

        this.localizacao = localizacao;
    }


    public SalaDTO(Sala sala)
    {
        this.nome = sala.getNome();
        this.capacidadeMaxima = sala.getCapacidadeMaxima();
        this.localizacao = sala.getLocalizacao();

    }
}
