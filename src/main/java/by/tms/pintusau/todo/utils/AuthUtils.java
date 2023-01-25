package by.tms.pintusau.todo.utils;

import by.tms.pintusau.todo.configs.security.CustomUserDetails;
import by.tms.pintusau.todo.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class AuthUtils {

    public User getCurrentUser() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUser();
    }
}
