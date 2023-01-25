package by.tms.pintusau.todo.repositories;

import by.tms.pintusau.todo.entities.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends AbstractRepository<Task> {

    @Modifying
    @Query("UPDATE Task task set task.name = :name, task.description = :description WHERE task.id = :id")
    void updateNameAndDescription(@Param("name") String name,
                                  @Param("description") String description,
                                  @Param("id") Long id);
}
