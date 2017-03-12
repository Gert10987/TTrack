package com.gert.model.user;

import java.io.Serializable;

/**
 * Created by gert on 03.03.17.
 */
public enum UserProfileType implements Serializable {

    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }

}
