package by.tms.pintusau.todo.controllers.rest;

import by.tms.pintusau.todo.dtos.Dto;
import by.tms.pintusau.todo.dtos.TaskCreateDto;
import by.tms.pintusau.todo.dtos.TaskUpdateDto;
import by.tms.pintusau.todo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Task", description = "Task API")
@RestController
@RequestMapping("/rest/tasks")
@AllArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks")
    public List<Dto> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    @Operation(summary = "Create task")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TaskCreateDto task) {
        taskService.create(task);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get task by id")
    public Dto findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update task")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Dto update(@RequestBody TaskUpdateDto task) {
        return taskService.update(task);
    }
}