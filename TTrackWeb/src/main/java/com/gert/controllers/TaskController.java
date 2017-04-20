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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by gert on 27.03.17.
 */
@Controller
@RequestMapping("/manage-employer")
@SessionAttributes("roles")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    EmployerService employerService;

    /**
     * This method will provide the medium to update an existing user.
     */



}
