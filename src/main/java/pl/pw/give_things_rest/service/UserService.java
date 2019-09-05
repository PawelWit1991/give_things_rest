package pl.pw.give_things_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.User;
import pl.pw.give_things_rest.repository.RoleRepository;
import pl.pw.give_things_rest.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    public User update(User user) {

        return userRepository.findById(user.getId()).map(userdb -> {
            if (user.getEmail() != null) {
                userdb.setEmail(user.getEmail());
            }
            return userRepository.save(userdb);
        }).orElse(null);

    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User findByUserId(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public Page<User> findAll(@PageableDefault Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}