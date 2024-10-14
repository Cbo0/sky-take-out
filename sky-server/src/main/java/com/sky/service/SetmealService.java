package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

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

    /**
     * Get set meal by id for updating page display back data
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * Update set meal
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);
}
