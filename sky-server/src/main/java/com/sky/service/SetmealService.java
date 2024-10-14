package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {

    /**
     * Add new set meal, and save the relationship of set meal and dish
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * Paging query for set meal
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Delete set meal
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
