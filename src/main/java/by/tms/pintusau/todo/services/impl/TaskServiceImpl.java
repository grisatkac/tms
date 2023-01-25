package by.tms.pintusau.todo.services.impl;

import by.tms.pintusau.todo.dtos.Dto;
import by.tms.pintusau.todo.dtos.TaskCreateDto;
import by.tms.pintusau.todo.dtos.TaskDto;
import by.tms.pintusau.todo.dtos.TaskUpdateDto;
import by.tms.pintusau.todo.entities.Task;
import by.tms.pintusau.todo.entities.User;
import by.tms.pintusau.todo.mappers.TaskMapper;
import by.tms.pintusau.todo.repositories.TaskRepository;
import by.tms.pintusau.todo.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    public Dto create(TaskCreateDto value) {
        Task task = taskMapper.toEntity(value);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public Dto update(TaskUpdateDto value) {
        Task task = taskMapper.toEntity(value);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    @Override
    public List<Dto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> (Dto) taskMapper.toDto(task))
                .toList();
    }

    @Override
    public TaskDto findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        }
    }
}
