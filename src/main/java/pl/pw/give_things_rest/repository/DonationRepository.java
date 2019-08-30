package pl.pw.give_things_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.give_things_rest.model.Donation;

public interface DonationRepository extends JpaRepository<Donation,Long> {
}
