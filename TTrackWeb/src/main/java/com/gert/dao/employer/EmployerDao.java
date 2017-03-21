package com.gert.dao.employer;

import com.gert.model.employer.Employer;
import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface EmployerDao {

    Employer findById(int id);

    Employer findBySSO(String sso);

    void save(Employer employer);

    void deleteBySSO(String sso);

    List<Employer> findAllEmployersByBossId(User user);

}
