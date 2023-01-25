package by.tms.pintusau.todo.services;

import by.tms.pintusau.todo.dtos.Dto;
import by.tms.pintusau.todo.dtos.TaskCreateDto;
import by.tms.pintusau.todo.dtos.TaskDto;
import by.tms.pintusau.todo.dtos.TaskUpdateDto;

import java.util.List;

public interface TaskService {

    Dto create(TaskCreateDto dto);

    Dto update(TaskUpdateDto dto);

    List<Dto> findAll();

    TaskDto findById(Long id);

    void delete(Long id);

}
