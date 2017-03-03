package com.gert.dao;

import com.gert.model.UserProfile;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
