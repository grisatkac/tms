package by.tms.pintusau.todo.repositories;

import by.tms.pintusau.todo.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
