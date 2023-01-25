package by.tms.pintusau.todo.repositories;

import by.tms.pintusau.todo.entities.Role;

public interface RoleRepository extends AbstractRepository<Role> {
    Role findByName(String name);
}
