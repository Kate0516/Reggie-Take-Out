package com.zhen.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.reggie.dto.DishDto;
import com.zhen.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品同时插入菜品对应的口味数据，需要操作两张表
    public void saveWithFlavor(DishDto dishDto);
    //根据id查询菜品信息和对应口味信息
    public DishDto getByIdWithFlavor(Long id);
    //更新菜品信息和口味信息
    public void updateWithFlavor(DishDto dishDto);
}
