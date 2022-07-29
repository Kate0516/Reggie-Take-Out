package com.zhen.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
