package by.tms.pintusau.todo.configs.security;

import by.tms.pintusau.todo.entities.Role;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserServiceDetailsTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testLoadUserByUsername() {
        String username = "test";
        User user = User.builder()
                .login(username)
                .password("test")
                .role(Role.builder().name("ROLE_USER").build())
                .build();
        when(userService.findByLogin(username)).thenReturn(user);

        CustomUserDetails actual = customUserDetailsService.loadUserByUsername(username);

        assertEquals(user.getLogin(), actual.getUsername());
        assertEquals(user.getPassword(), actual.getPassword());
        assertTrue(actual.getAuthorities().contains(new SimpleGrantedAuthority(user.getRole().getName())));
        verify(userService).findByLogin(username);
    }
}
