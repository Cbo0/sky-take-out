package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * Employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add employee
     * @param employeeDTO
     */

    void save(EmployeeDTO employeeDTO);

    /**
     * Paging query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Start or stop employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query employee information according to the id
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * Update employee personal information
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
