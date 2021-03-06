package com.gert.dao.employer;

import com.gert.dao.AbstractDao;
import com.gert.model.employer.Employer;
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
@Repository("employerDao")
public class EmployerDaoImpl extends AbstractDao<Integer, Employer> implements EmployerDao {

    static final Logger logger = LoggerFactory.getLogger(EmployerDaoImpl.class);

    @SuppressWarnings("unchecked")
    public List<Employer> findAllEmployersByBossId(User boss) {

        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.add(Restrictions.eq("user", boss));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Employer> employers = (List<Employer>) criteria.list();

        return employers;
    }

    public Employer findById(int id) {

        logger.info("Employer ID : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Employer employer = (Employer) crit.uniqueResult();

        if(employer != null){

            Hibernate.initialize(employer);
        }
        return employer;
    }

    public Employer findBySSO(String sso) {

        logger.info("SSO : {}", sso);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        Employer employer = (Employer) crit.uniqueResult();

        if(employer != null){

            Hibernate.initialize(employer);
        }
        return employer;
    }

    public void save(Employer employer) {
        persist(employer);
    }

    public void deleteBySSO(String sso) {

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        Employer employer = (Employer) crit.uniqueResult();
        delete(employer);
    }

}
