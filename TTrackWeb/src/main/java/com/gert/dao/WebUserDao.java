package com.gert.dao;

import com.gert.model.WebUser;

import java.util.List;

/**
 * Created by gert on 02.03.17.
 */
public interface WebUserDao {

    void saveOrUpdate(WebUser contact);
    void delete(int contactId);
    WebUser get(int contactId);
    List<WebUser> list();
}
