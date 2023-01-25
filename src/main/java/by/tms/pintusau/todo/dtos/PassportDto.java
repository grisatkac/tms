package by.tms.pintusau.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto implements Dto {

    @Size(min = 5, max = 20, message = "Serial number should be between 5 to 20 characters")
    private String serial;
}
