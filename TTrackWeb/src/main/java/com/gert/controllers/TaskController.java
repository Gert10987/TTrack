package com.gert.controllers;

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
}
