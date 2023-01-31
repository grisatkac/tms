package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.dtos.PassportDto;
import by.tms.pintusau.todo.entities.Role;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.repositories.RoleRepository;
import by.tms.pintusau.todo.repositories.TaskRepository;
import by.tms.pintusau.todo.repositories.UserRepository;
import by.tms.pintusau.todo.services.PassportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class, FlywayAutoConfiguration.class})
public class PassportMvcTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PassportService passportService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private TaskRepository taskRepository;

    @Test
    @WithMockUser
    void testCreate() throws Exception {
        String serial = "123-TEST-SERIAL";
        PassportDto passportDto = PassportDto.builder()
                .serial(serial)
                .build();
        String username = "test";
        User user = User.builder()
                .login(username)
                .password("test")
                .role(Role.builder().name("ROLE_USER").build())
                .build();

        when(userRepository.findByLogin(anyString())).thenReturn(user);
        when(passportService.create(passportDto)).thenReturn(passportDto);

        mvc.perform(post("/passports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passportDto)))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(view().name("index"));
    }
}
