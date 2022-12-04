package com.forcode.base.common.exception;

import com.forcode.base.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        if (e instanceof DataAccessException) {
            return error((DataAccessException) e);
        }
        log.error("===业务异常: ", e);
        return Result.error("服务繁忙! 请稍后再试。");
    }

    @ExceptionHandler(BizException.class)
    public Result error(BizException e) {
        log.error("===业务异常: ", e);
        return Result.error(e.getMessage());
    }

    /**
     * 数据库异常信息
     */
    @ExceptionHandler(DataAccessException.class)
    public Result error(DataAccessException e) {
        log.error("===数据库异常: ", e);
        return Result.error("数据异常, 请稍后再试!");
    }

    @ExceptionHandler(BindException.class)
    public Result error(BindException e) {
        log.error("===参数校验异常: ", e);
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return Result.error(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result error(MethodArgumentNotValidException e) {
        log.error("===参数校验异常: ", e);
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return Result.error(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result error(ConstraintViolationException e) {
        log.error("===参数校验异常: ", e);
        String errorMessage = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining());
        return Result.error(errorMessage);
    }
}
