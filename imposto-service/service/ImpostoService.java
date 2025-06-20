package imposto.service.service;

import imposto.service.model.Imposto;
import imposto.service.repository.ImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ImpostoService {

    @Autowired
    private ImpostoRepository repository;

    public Imposto salvar(Imposto imposto) {
        return repository.save(imposto);
    }

    public Map<String, BigDecimal> getResumo(int mes, int ano) {
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Imposto> registros = repository.findByDataBetween(start, end);

        BigDecimal totalVendido = registros.stream()
                .map(Imposto::getValorVenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalImposto = registros.stream()
                .map(Imposto::getValorImposto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(
            "totalVendido", totalVendido,
            "totalImposto", totalImposto
        );
    }
}
