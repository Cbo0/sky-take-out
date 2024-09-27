package com.sky.service;

import com.sky.dto.SetmealDTO;

public interface SetmealService {

    /**
     * Add new set meal, and save the relationship of set meal and dish
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
