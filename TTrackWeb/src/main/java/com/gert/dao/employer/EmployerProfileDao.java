package com.gert.dao.employer;

import com.gert.model.user.UserProfile;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface EmployerProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
