package com.gert.dao.task;

import com.gert.dao.AbstractDao;
import com.gert.dao.employer.EmployerDao;
import com.gert.model.employer.Employer;
import com.gert.model.task.Task;
import com.gert.model.user.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao<Integer, Task> implements TaskDao {

    static final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);

    @SuppressWarnings("unchecked")
    public List<Employer> findAllEmployersByBossId(User boss) {

        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.add(Restrictions.eq("user", boss));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Employer> employers = (List<Employer>) criteria.list();

        return employers;
    }

    public Task findById(int id) {

        logger.info("Task ID : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Task task = (Task) crit.uniqueResult();

        if(task != null){

            Hibernate.initialize(task);
        }
        return task;
    }

    public Task findByName(String name) {

        logger.info("Name : {}", name);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        Task task = (Task) crit.uniqueResult();

        if(task != null){

            Hibernate.initialize(task);
        }
        return task;
    }

    public void save(Task task) {
        persist(task);
    }

    public void deleteByName(String name) {

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        Task task = (Task) crit.uniqueResult();
        delete(task);
    }

    public List<Task> findAllTaskByEmployerId(Employer employer) {
        return null;
    }

    public Task findLastTaskForEmployer(String employerId) {
        return null;
    }

}
