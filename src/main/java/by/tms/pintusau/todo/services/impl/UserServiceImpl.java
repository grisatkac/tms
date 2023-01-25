package by.tms.pintusau.todo.services.impl;

import by.tms.pintusau.todo.entities.Role;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.exceptions.ResourceNotFoundException;
import by.tms.pintusau.todo.repositories.RoleRepository;
import by.tms.pintusau.todo.repositories.UserRepository;
import by.tms.pintusau.todo.services.UserService;
import by.tms.pintusau.todo.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteById() {
        User currentUser = AuthUtils.getCurrentUser();
        userRepository.deleteById(currentUser.getId());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findDistinctByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cannot find user by first name=%s and last name=%s", firstName, lastName)
                ));
    }

    @Override
    public Page<User> getByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
