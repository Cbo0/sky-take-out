package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    // Query the corresponding set meal id based on the dish id
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    // Batch save association between set meal and dishes
    void insertBatch(List<SetmealDish> setmealDishes);
}
