package com.turingcourt.exception;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultCode;
import com.turingcourt.config.json.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cyanogen
 * @description 异常处理
 * @date 2022/3/27 9:26
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * 实体对象校验异常
     *
     * @param e 异常
     * @return json数据
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public JsonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return ResultTool.fail(ResultCode.PARAM_IS_BLANK, message);
    }

    /**
     * 单个参数校验时抛出的异常
     *
     * @param e 异常
     * @return json数据
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public JsonResult handleBindGetException(ConstraintViolationException e) {
        //获取参数校验异常message
        List<String> defaultMsg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        String message = defaultMsg.get(0);
        return ResultTool.fail(ResultCode.PARAM_IS_BLANK, message);
    }

}

