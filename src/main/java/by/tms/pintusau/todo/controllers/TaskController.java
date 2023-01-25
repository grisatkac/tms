package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.dtos.TaskCreateDto;
import by.tms.pintusau.todo.dtos.TaskUpdateDto;
import by.tms.pintusau.todo.entities.Task;
import by.tms.pintusau.todo.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @ModelAttribute("task")
    public Task getTask() {
        return Task.builder().build();
    }

    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "all-tasks";
    }

    @GetMapping("/add")
    public String form() {
        return "add-task";
    }

    @PostMapping("/add")
    public String add(@Valid TaskCreateDto task, BindingResult result) {
        if (result.hasErrors()) {
            return "add-task";
        }

        taskService.create(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid TaskUpdateDto updateDto, BindingResult result) {
        if (result.hasErrors()) {
            updateDto.setId(id);
            return "update-task";
        }

        taskService.update(updateDto);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}
