public interface TaxRecordRepository extends JpaRepository<TaxRecord, Long> {
    List<TaxRecord> findByDataBetween(LocalDate start, LocalDate end);
}
