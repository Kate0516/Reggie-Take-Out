package com.zhen.reggie.common;

/**
 * use to save and get login user's id, base on threadlocal
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * set id
     * @param id
     */
    public static void setCurrentId(long id){
        threadLocal.set(id);
    }

    /**
     * get id
     * @return
     */
    public static Long getCurrentId(){
        return  threadLocal.get();
    }
}
