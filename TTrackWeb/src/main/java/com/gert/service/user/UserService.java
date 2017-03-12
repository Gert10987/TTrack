package com.gert.service.user;

import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}