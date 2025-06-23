package com.bcopstein.historyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bcopstein.historyservice.ServicoDeRegistroDeVendas;

@RestController
@RequestMapping("/registro-vendas")
public class RegistroDeVendaController {

    private final ServicoDeRegistroDeVendas service;

    public RegistroDeVendaController(ServicoDeRegistroDeVendas service) {
        this.service = service;
    }

    @GetMapping("/totais")
    public ServicoDeRegistroDeVendas.TotaisMesDTO getTotaisMes(
        @RequestParam int ano,
        @RequestParam int mes
    ) {
        return service.totaisDoMes(ano, mes);
    }

}
