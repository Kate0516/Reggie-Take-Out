package com.zhen.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.reggie.common.CustomException;
import com.zhen.reggie.entity.Category;
import com.zhen.reggie.entity.Dish;
import com.zhen.reggie.entity.Setmeal;
import com.zhen.reggie.mapper.CategoryMapper;
import com.zhen.reggie.service.CategoryService;
import com.zhen.reggie.service.DishService;
import com.zhen.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * delete by id, check before delete
     * @param id
     */
    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        //if this category has relation with dishes
        if(count > 0){
            throw  new CustomException("this category is related with dishes");
        }

        //if this categoty has relation with set meals
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count();
        if(count2 > 0){
            throw  new CustomException("this category is related with set meals");
        }
    }
}
