package com.zhen.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * self defined meta data handler
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * auto fill when insert
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("public field auto fill(insert)");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    /**
     * autofill when modifying
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("public field auto fill(update)");

        long id = Thread.currentThread().getId();
        log.info("thread id is:{}", id);

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
        log.info(metaObject.toString());
    }
}
