package com.gert.service.user;

import com.gert.model.user.UserProfile;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
