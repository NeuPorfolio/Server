package com.neuporfolio.server.api;


import com.neuporfolio.server.utils.formformat.ComFailureForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 截获所有异常
 */
@ControllerAdvice
public class AllExceptionCatch {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> anyException(Exception ex) {
        ex.printStackTrace();
        return new ComFailureForm(403, ex.getMessage()).toResponseEntity();
    }

}
