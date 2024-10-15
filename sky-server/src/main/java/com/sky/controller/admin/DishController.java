package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Query dishes based on id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Query dishes based on id")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("Query dishes based on id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Update dish information
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update dish information")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("Update dish information, {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Query dishes based on category id
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query dishes based on category id")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * Starting or stoping dish
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Starting or stoping dish")
    public Result startOrStop(@PathVariable Integer status, Long id){
        dishService.startOrStop(status, id);
        return Result.success();
    }
}
