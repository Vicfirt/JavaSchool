package com.javaschool.onlineshop.Exception;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class MyExceptionHandler {

    private final Logger logger;

    public MyExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(MyException.class)
    public String errorHandler(MyException exception, Model model) {
        logger.error("ErrorLog", exception);
        model.addAttribute("message", exception.getMessage());
        return "myerror";
    }
}
