package com.gert.tools;

/**
 * Created by gert on 08.04.17.
 */
public class TaskTools {

    public static int getNextId(int lastId, int currentId){

        int res = currentId;

        if(currentId >= lastId){

            return res;
        }
        return ++res;
    }

    public static int getPrevId(int currentId){

        int res = currentId;

        if(currentId <= 0){

            return res;
        }
        return --res;
    }
}
