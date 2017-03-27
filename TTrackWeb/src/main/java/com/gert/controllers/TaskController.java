package com.gert.controllers;

import com.gert.service.employer.EmployerService;
import com.gert.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by gert on 27.03.17.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class TaskController {

    @Autowired
    TaskService taskServicel;
}
