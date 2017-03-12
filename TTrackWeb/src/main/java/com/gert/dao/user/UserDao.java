package com.gert.dao.user;

import com.gert.model.user.User;

import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
