package by.tms.pintusau.todo.services;

import by.tms.pintusau.todo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User create(User user);

    User update(User user);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    void deleteById();

    void deleteById(Long id);

    User findByFirstNameAndLastName(String firstName, String lastName);

    Page<User> getByPage(Pageable pageable);
}
