package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User save(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping
    public User findByFirstNameLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.findByFirstNameAndLastName(firstName, lastName);
    }

    @DeleteMapping
    public void delete() {
        userService.deleteById();
    }

    @GetMapping("/page")
    public Page<User> getByPage(Pageable pageable) {
        return userService.getByPage(pageable);
    }
}
