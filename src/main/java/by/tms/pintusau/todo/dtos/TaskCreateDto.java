package by.tms.pintusau.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto implements Dto {
    @NotNull(message = "Task name is required")
    @Length(min = 3, max = 100, message = "Task name should be between 3 to 100 characters")
    private String name;
    @NotNull(message = "Task description is required")
    @Length(min = 3, max = 1000, message = "Task description should be between 3 to 100 characters")
    private String description;
}
