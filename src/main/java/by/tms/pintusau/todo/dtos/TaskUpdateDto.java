package by.tms.pintusau.todo.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto extends TaskDto {

    private Long id;
}
