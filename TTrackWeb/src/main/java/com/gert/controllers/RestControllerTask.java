package com.gert.controllers;

import com.gert.model.task.Task;
import com.gert.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by gert on 16.05.17.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restApi")
public class RestControllerTask {

    @Autowired
    TaskService taskService;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/task/all/{employerSsoId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Task>> listAllUsers(@PathVariable("employerSsoId") String employerSsoId) {
        List<Task> tasks = taskService.findAllTasksByEmployerSsoId(employerSsoId);
        if(tasks.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Task> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Task task = taskService.findById(id);
        if (task == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }



    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/task/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Task " + task.getName());
//
//        if (taskService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        taskService.saveTask(task);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateUser(@PathVariable("id") int id, @RequestBody Task task) {
        System.out.println("Updating Task " + id);

        Task currentTask = taskService.findById(id);

        if (currentTask == null) {
            System.out.println("Task with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        currentTask.setName(task.getName());

        taskService.updateTask(currentTask);
        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Task task = taskService.findById(id);
        if (task == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        taskService.deleteTaskByName(task.getName());
        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All Users --------------------------------------------------------
//
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<Task> deleteAllUsers() {
//        System.out.println("Deleting All Users");
//
//        taskService.deleteAllTasks();
//        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
//    }
}
