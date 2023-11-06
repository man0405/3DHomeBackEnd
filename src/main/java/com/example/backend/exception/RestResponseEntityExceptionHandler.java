package com.example.backend.exception;

import com.example.backend.dto.ResponseErrorTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomMessageException.class)
    public ResponseEntity<ResponseErrorTemplate> handleErrorException(CustomMessageException ex ){
        return ResponseEntity.ok(
                new ResponseErrorTemplate(
                        ex.getMessage(), ex.getCode(), null
                )
    );
//        ResponseErrorTemplate errorResponse = new ResponseErrorTemplate(ex.getMessage(), ex.getCode(), null);
//        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(Integer.parseInt(ex.getCode())));
    }
}