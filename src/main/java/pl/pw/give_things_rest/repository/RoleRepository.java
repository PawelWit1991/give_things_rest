package pl.pw.give_things_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.give_things_rest.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
