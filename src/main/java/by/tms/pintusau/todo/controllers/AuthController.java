package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.dtos.auth.AuthRequest;
import by.tms.pintusau.todo.dtos.auth.AuthResponse;
import by.tms.pintusau.todo.dtos.auth.RegistrationRequest;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.mappers.UserMapper;
import by.tms.pintusau.todo.services.UserService;
import by.tms.pintusau.todo.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/rest/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = JwtUtils.generateToken(user.getLogin());
        return AuthResponse.builder().token(token).build();
    }

    @PostMapping("/rest/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = userMapper.toEntity(registrationRequest);
        userService.create(user);
    }
}
