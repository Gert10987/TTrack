package com.gert.controllers;

import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import com.gert.service.employer.EmployerService;
import com.gert.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by gert on 27.03.17.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    EmployerService employerService;

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/manage-employer-{ssoId}-task-{task}"}, method = RequestMethod.GET)
    public String editEmployer(@PathVariable String ssoId, @PathVariable String task, ModelMap model) {

        Employer employer = employerService.findBySSO(ssoId);
        List<Task> tasks = taskService.findAllTasksByEmployer(employer);

        int currentTaskId = Integer.parseInt(task);
        int lastTaskId = tasks.size() - 1;

        model.addAttribute("employer", employer);
        model.addAttribute("task", tasks.get(currentTaskId));
        model.addAttribute("edit", true);
        model.addAttribute("nextTaskId", getNextId(lastTaskId, currentTaskId));
        model.addAttribute("previouslyTaskId", getPrevId(currentTaskId));

        return "manageEmployer";
    }

    private int getNextId(int lastId, int currentId){

        int res = currentId;

        if(currentId >= lastId){

            return res;
        }
        return ++res;
    }

    private int getPrevId(int currentId){

        int res = currentId;

        if(currentId <= 0){

            return res;
        }
        return --res;
    }
}
