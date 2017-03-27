package com.gert.dao.task;

import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface TaskDao {

    Task findById(int id);

    Task findByName(String name);

    void save(Task task);

    void deleteByName(String sso);

    List<Task> findAllTaskByEmployerId(Employer employer);

}
