package exercicio04.exerciciosalas.Controller;

import exercicio04.exerciciosalas.Entity.DTO.ReservaDTO;
import exercicio04.exerciciosalas.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO novaReserva = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.ok(novaReserva);
    }


    @GetMapping("/futuras")
    public ResponseEntity<List<ReservaDTO>> listarReservas() {
        List<ReservaDTO> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas);
    }


    @GetMapping("/sala/{idSala}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorSala(@PathVariable Long idSala) {
        List<ReservaDTO> reservas = reservaService.listarReservasPorSala(idSala);
        return ResponseEntity.ok(reservas);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
