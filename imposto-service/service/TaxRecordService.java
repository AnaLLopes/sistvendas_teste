@Service
public class TaxRecordService {
    @Autowired
    private TaxRecordRepository repository;

    public TaxRecord salvar(TaxRecord registro) {
        return repository.save(registro);
    }

    public Map<String, BigDecimal> getResumo(int mes, int ano) {
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<TaxRecord> registros = repository.findByDataBetween(start, end);

        BigDecimal totalVendido = registros.stream()
                .map(TaxRecord::getValorVenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalImposto = registros.stream()
                .map(TaxRecord::getValorImposto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(
            "totalVendido", totalVendido,
            "totalImposto", totalImposto
        );
    }
}
