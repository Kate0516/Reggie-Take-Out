package com.zhen.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.reggie.dto.SetmealDto;
import com.zhen.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐同时保存菜品
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐和菜品
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
