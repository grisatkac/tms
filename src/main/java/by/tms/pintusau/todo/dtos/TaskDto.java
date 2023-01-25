package by.tms.pintusau.todo.dtos;

import by.tms.pintusau.todo.entities.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto extends TaskCreateDto {

    private LocalDate createdDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
