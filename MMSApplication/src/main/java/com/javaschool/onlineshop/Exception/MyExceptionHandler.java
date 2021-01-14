package com.javaschool.onlineshop.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public String errorHandler(MyException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "myerror";
    }
}
