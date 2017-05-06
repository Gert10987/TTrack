package com.gert.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView editEmployer(@PathVariable String ssoId, @PathVariable String task, ModelMap model) throws JsonProcessingException {

        Employer employer = employerService.findBySSO(ssoId);
        List<Task> tasks = taskService.findAllTasksByEmployer(employer);

        ObjectMapper mapper = new ObjectMapper();

        int currentTaskId = Integer.parseInt(task);
        int lastTaskId = tasks.size() - 1;

        ModelAndView modelAndView = new ModelAndView("employer/manageEmployer");

        modelAndView.addObject("employer", employer);
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("edit", true);
        modelAndView.addObject("nextTaskId", TaskTools.getNextId(lastTaskId, currentTaskId));
        modelAndView.addObject("previouslyTaskId", TaskTools.getPrevId(currentTaskId));

        if (tasks.size() == Integer.parseInt(task)) {

            Task newTask = new Task();
            newTask.setEmployer(employer);

            modelAndView.addObject("currentTask", newTask);
            modelAndView.addObject("currentTaskJson", mapper.writeValueAsString(newTask));

        } else {

            Task curentTask = tasks.get(currentTaskId);

            modelAndView.addObject("currentTask", curentTask);
            modelAndView.addObject("currentTaskJson", mapper.writeValueAsString((curentTask)));
        }

        return modelAndView;
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

            Employer employer = employerService.findById(currentTask.getEmployer().getId());
            currentTask.setEmployer(employer);
            if (currentTask.getId() != null)
                taskService.updateTask(currentTask);
            else
                taskService.saveTask(currentTask);
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
