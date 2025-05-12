package exercicio04.exerciciosalas.Controller;

import exercicio04.exerciciosalas.Entity.Sala;
import exercicio04.exerciciosalas.Service.SalasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalasController {

    @Autowired
    private SalasService salasService;


    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody Sala sala) {
        Sala novaSala = salasService.criarSala(sala);
        return ResponseEntity.ok(novaSala);
    }


    @GetMapping
    public ResponseEntity<List<Sala>> listarSalas() {
        return ResponseEntity.ok(salasService.listarSalas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = salasService.buscarPorId(id);
        return ResponseEntity.ok(sala);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizarSala(@PathVariable Long id, @RequestBody Sala salaAtualizada) {
        Sala atualizada = salasService.atualizarSala(id, salaAtualizada);
        return ResponseEntity.ok(atualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salasService.removerSala(id);
        return ResponseEntity.noContent().build();
    }
}
