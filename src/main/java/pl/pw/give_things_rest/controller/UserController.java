package pl.pw.give_things_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.pw.give_things_rest.model.User;
import pl.pw.give_things_rest.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Page<User> all(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public User read(@PathVariable Long id) {
        return userService.findByUserId(id);
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
