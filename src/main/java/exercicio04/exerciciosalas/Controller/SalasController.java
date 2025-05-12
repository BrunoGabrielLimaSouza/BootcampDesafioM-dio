package exercicio04.exerciciosalas.Controller;

import exercicio04.exerciciosalas.Entity.DTO.SalaDTO;
import exercicio04.exerciciosalas.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalasController {

    @Autowired
    private SalaService salaService;


    @PostMapping
    public ResponseEntity<SalaDTO> criarSala(@RequestBody SalaDTO sala) {
        SalaDTO novaSala = salaService.criarSala(sala);
        return ResponseEntity.ok(novaSala);
    }


    @GetMapping
    public ResponseEntity<List<SalaDTO>> listarSalas() {
        return ResponseEntity.ok(salaService.listarSalas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> buscarPorId(@PathVariable Long id) {
        SalaDTO sala = salaService.buscarPorId(id);
        return ResponseEntity.ok(sala);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> atualizarSala(@PathVariable Long id, @RequestBody SalaDTO salaAtualizada) {
        SalaDTO atualizada = salaService.atualizarSala(id, salaAtualizada);
        return ResponseEntity.ok(atualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.removerSala(id);
        return ResponseEntity.noContent().build();
    }
}
