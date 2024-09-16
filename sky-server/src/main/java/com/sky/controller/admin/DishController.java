package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish management
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "Dish related interface")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * Add dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("Add dish, {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Pagination query for dishes
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Pagination query for dishes")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("Pagination query for dishes:{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete dish
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete dish")
    public Result delete(@RequestParam List<Long> ids){
        log.info("Delete dish, {}", ids);
        dishService.deleteBench(ids);
        return Result.success();
    }
}
