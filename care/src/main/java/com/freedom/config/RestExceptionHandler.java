package com.freedom.config;

import com.freedom.common.model.ResultModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private static Log log = LogFactory.getLog(RestExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    private ResultModel runtimeExceptionHandler(Exception e) {
        log.error("未知错误", e);
        return ResultModel.error(e.getMessage());
    }

}

