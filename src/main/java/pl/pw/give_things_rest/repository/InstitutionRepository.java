package pl.pw.give_things_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.give_things_rest.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
