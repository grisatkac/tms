package by.tms.pintusau.todo.dtos.auth;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegistrationRequest {
    @Pattern(regexp = "\\w{3,15}",
            message = "Login should contain only latin symbols and numbers with underscore. " +
                    "Length from 3 up to 15 characters.")
    String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    String password;
}
