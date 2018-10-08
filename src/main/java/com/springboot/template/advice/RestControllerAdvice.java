package com.springboot.template.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jinyu on 2018/9/30.
 *
 * @ControllerAdvice 注解内部使用@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
 *           @ModelAttribute  @InitBinder 要全局注册时比较有用。
 *           @ExceptionHandler 可以接受一个当前抛出的Throwable对象
 *
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> runtimeExceptionHandler(Exception e) {
        LOGGER.error("---------> huge error!", e);
        return RestResultGenerator.genErrorResult(ErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("---------> invalid request!", e);
        return RestResultGenerator.genErrorResult(ErrorCode.ILLEGAL_PARAMS);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> classNotFoundExceptionHandler(ClassNotFoundException e) {
        LOGGER.debug("---------> invalid request!", e);
        return RestResultGenerator.genErrorResult(ErrorCode.CLASS_NOT_FOUND);
    }
}
