package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * Add dish and flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * Pagination query for dishes
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Delete dish
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Query dishes based on id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Update dish information
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Query dishes based on category id
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}
