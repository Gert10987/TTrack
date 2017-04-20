package com.gert.controllers;

import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import com.gert.service.employer.EmployerService;
import com.gert.service.task.TaskService;
import com.gert.tools.TaskTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by gert on 27.03.17.
 */
@Controller
@RequestMapping
@SessionAttributes("roles")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    EmployerService employerService;

    @RequestMapping(value = {"/manage-employer-{ssoId}-task-{task}"}, method = RequestMethod.GET)
    public String editEmployer(@PathVariable String ssoId, @PathVariable String task, ModelMap model) {

        Employer employer = employerService.findBySSO(ssoId);
        List<Task> tasks = taskService.findAllTasksByEmployer(employer);

        int currentTaskId = Integer.parseInt(task);
        int lastTaskId = tasks.size() - 1;

        model.addAttribute("employer", employer);
        model.addAttribute("currentTask", tasks.get(currentTaskId));
        model.addAttribute("tasks", tasks);
        model.addAttribute("edit", true);
        model.addAttribute("nextTaskId", TaskTools.getNextId(lastTaskId, currentTaskId));
        model.addAttribute("previouslyTaskId", TaskTools.getPrevId(currentTaskId));

        return "employer/manageEmployer";
    }

    @RequestMapping(value = {"/manage-employer/tasks-{ssoId}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Task> listOfTasks(@PathVariable String ssoId) {

        Employer currentEmployer = employerService.findBySSO(ssoId);
        List<Task> tasks = taskService.findAllTasksByEmployer(currentEmployer);

        return tasks;
    }

    @RequestMapping(value = {"/manage-employer-{ssoId}-task-{task}"}, method = RequestMethod.POST)
    public String updateEmployer(@Valid Task currentTask, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {

            return "common/registrationEmployer";

        } else {

            taskService.updateTask(currentTask);
        }
        return "redirect:/manage-employer-"
                + currentTask.getEmployer().getSsoId()
                + "-task-0";
    }

    @RequestMapping(value = {"/manage-employer/delete-task-{taskId}"}, method = RequestMethod.POST)
    public String deleteEmployer(@Valid Task currentTask, BindingResult result, ModelMap model) {

        taskService.deleteTaskByName(currentTask.getName());

        return "redirect:/manage-employer-"
                + currentTask.getEmployer().getSsoId()
                + "-task-0";
    }
}
