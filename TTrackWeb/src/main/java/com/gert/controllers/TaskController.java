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
    @RequestMapping(value = {"/manage-employer-{ssoId}"}, method = RequestMethod.GET)
    public String editEmployer(@PathVariable String ssoId, ModelMap model) {

        Employer employer = employerService.findBySSO(ssoId);
        List<Task> tasks = taskService.findAllTasksByEmployer(employer);

        model.addAttribute("employer", employer);
        model.addAttribute("task", tasks.get(0));
        model.addAttribute("edit", true);

        return "manageEmployer";
    }
}
