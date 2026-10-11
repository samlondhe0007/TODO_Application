package com.todo.todoapp.controller;

import org.springframework.ui.Model;
import com.todo.todoapp.models.Task;
import com.todo.todoapp.services.TaskServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServices TaskServices;
    TaskController(TaskServices TaskServices) {
        this.TaskServices = TaskServices;
    }
    @GetMapping("/getAllTasks")
    public String getTasks(Model model) {
        List<Task> tasks = TaskServices.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @PostMapping("/addTasks")
    public String addTasks(@RequestParam("title") String title) {
        TaskServices.addTask(title);
        return "redirect:/tasks/getAllTasks";
    }
    @GetMapping("/deleteTask")
    public String deleteTasks(@RequestParam("id") Long id) {
        TaskServices.deleteTask(id);
        return "redirect:/tasks/getAllTasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable("id") Long id) {
        TaskServices.toggleTask(id);
        return "redirect:/tasks/getAllTasks";
    }

}
