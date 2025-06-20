@RestController
@RequestMapping("/impostos")
public class TaxRecordController {

    @Autowired
    private TaxRecordService service;

    @PostMapping
    public ResponseEntity<TaxRecord> registrar(@RequestBody TaxRecord record) {
        return ResponseEntity.ok(service.salvar(record));
    }

    @GetMapping("/resumo")
    public ResponseEntity<Map<String, BigDecimal>> resumo(
        @RequestParam int mes,
        @RequestParam int ano
    ) {
        return ResponseEntity.ok(service.getResumo(mes, ano));
    }
}
