package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Setmeal management
 */

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "Interface for set meal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * Add new set meal
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new set meal")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * Paging query for set meal
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Paging query for set meal")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete set meal
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete set meal")
    public Result delete(@RequestParam List<Long> ids){
        setmealService.deleteBatch(ids);
        return Result.success();
    }
}
