package by.tms.pintusau.todo.repositories;

import by.tms.pintusau.todo.entities.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findDistinctByFirstNameAndLastName(String firstName, String lastName);

    User findByLogin(String login);
}
