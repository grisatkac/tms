package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.dtos.auth.RegistrationRequest;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class PublicController {

    private final UserService userService;

    @ModelAttribute("registrationRequest")
    public RegistrationRequest getRegistrationRequest() {
        return new RegistrationRequest();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = User.builder()
                .login(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .build();
        userService.create(user);
        return "redirect:/";
    }
}
