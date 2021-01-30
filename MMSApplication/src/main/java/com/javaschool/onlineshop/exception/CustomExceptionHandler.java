package com.javaschool.onlineshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Component
public class CustomExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(DataNotFoundException.class)
    public String userErrorHandler(DataNotFoundException exception, Model model) {
        LOGGER.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "custom_error";
    }

    @ExceptionHandler(FileTransferException.class)
    public String notFoundHandler(FileTransferException exception, Model model) {
        LOGGER.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "custome_error";
    }
}
