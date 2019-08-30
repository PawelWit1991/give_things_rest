package pl.pw.give_things_rest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.give_things_rest.model.User;


public interface UserRepository extends JpaRepository<User, Long> {



}
