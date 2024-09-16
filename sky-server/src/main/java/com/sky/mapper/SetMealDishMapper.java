package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    // Query the corresponding set meal id based on the dish id
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);
}
