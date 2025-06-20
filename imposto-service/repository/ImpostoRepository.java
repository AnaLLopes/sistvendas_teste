package imposto.service.repository;

import imposto.service.model.Imposto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ImpostoRepository extends JpaRepository<Imposto, Long> {
    List<Imposto> findByDataBetween(LocalDate start, LocalDate end);
}
