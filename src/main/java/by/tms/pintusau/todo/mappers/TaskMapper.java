package by.tms.pintusau.todo.mappers;

import by.tms.pintusau.todo.dtos.TaskCreateDto;
import by.tms.pintusau.todo.dtos.TaskUpdateDto;
import by.tms.pintusau.todo.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface TaskMapper {

    @Mapping(target = "status", constant = "TO_DO")
    @Mapping(target = "createdDate", expression = "java(LocalDate.now())")
    Task toEntity(TaskCreateDto taskDto);

    Task toEntity(TaskUpdateDto taskDto);

    TaskUpdateDto toDto(Task task);
}
