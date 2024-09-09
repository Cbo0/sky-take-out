package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Custom aspects to implement the common field automatic filling processing logic
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * Pointcut
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut(){}

    /**
     * Pre-notification, assign values to public fields in the notification
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinpoint){
        log.info("Start filling in public fields");

        //Get the database operation type on the currently intercepted method
        MethodSignature signature =(MethodSignature) joinpoint.getSignature();  //Method signature object
        AutoFill autofill = signature.getMethod().getAnnotation(AutoFill.class);    //Get the annotation object
        OperationType operationType = autofill.value(); //Get the database operation type

        //Get the parameters of the currently intercepted method - entity object
        Object[] args = joinpoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];

        //Data to be assigned
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //According to the current operation type, assign values to the corresponding attributes through reflection
        if(operationType == OperationType.INSERT){
            //4 public field assignments
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //Assign values to objects through reflection
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else if(operationType == OperationType.UPDATE){
            //2 public field assignments
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
