package com.gert.service.task;

import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface TaskService {

    Task findById(int id);

    Task findByName(String name);

    void saveTask(Task task);

    void updateTask(Task task);

    void createNewTask(Task task);

    void deleteTaskByName(String name);

    List<Task> findAllTasksByEmployer(Employer employer);
}