package com.gert.service.employer;

import com.gert.model.employer.Employer;
import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface EmployerService {

    Employer findById(int id);

    Employer findBySSO(String sso);

    void saveUser(Employer employer);

    void updateUser(Employer employer);

    void deleteUserBySSO(String sso);

    List<Employer> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}