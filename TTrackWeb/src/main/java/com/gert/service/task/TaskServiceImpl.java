package com.gert.service.task;

import com.gert.dao.task.TaskDao;
import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao dao;

    public Task findById(int id) {
        return dao.findById(id);
    }

    public Task findByName(String name) {

        Task task = dao.findByName(name);
        return task;
    }

    public void saveUser(Task task) {

        dao.save(task);
    }

    public void createNewTask(Task task) {

        Task taskEntity = dao.findById(task.getId());

        if(taskEntity != null){

            taskEntity.setName(task.getName());
            taskEntity.setDescription(task.getDescription());
            taskEntity.setStartDate(task.getStartDate());
            taskEntity.setEndDate(task.getEndDate());
            taskEntity.setEmailOfClient(task.getEmailOfClient());
            taskEntity.setPhoneOfClient(task.getPhoneOfClient());
            taskEntity.setStartOfTheRout(task.getStartOfTheRout());
            taskEntity.setEndOfTheRout(task.getEndOfTheRout());
        }
    }

    public void deleteTaskByName(String name) {
        dao.deleteByName(name);
    }

    public List<Task> findAllEmployersByBossId(Employer employer) {

        return dao.findAllTaskByEmployerId(employer);
    }

    public Task findLastTaskForEmployer(String employerId) {

        Task task = dao.findLastTaskForEmployer(employerId);
        return task;
    }
}
