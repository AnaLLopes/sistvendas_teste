package imposto.service.controller;

import imposto.service.model.Imposto;
import imposto.service.service.ImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/imposto")
public class ImpostoController {

    @Autowired
    private ImpostoService service;

    @PostMapping
    public ResponseEntity<Imposto> registrar(@RequestBody Imposto imposto) {
        return ResponseEntity.ok(service.salvar(imposto));
    }

    @GetMapping("/resumo")
    public ResponseEntity<Map<String, BigDecimal>> resumo(
        @RequestParam int mes,
        @RequestParam int ano
    ) {
        return ResponseEntity.ok(service.getResumo(mes, ano));
    }
}
