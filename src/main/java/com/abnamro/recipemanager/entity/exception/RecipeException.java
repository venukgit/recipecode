package com.abnamro.recipemanager.entity.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class RecipeException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);

        FieldError fieldError = ex.getBindingResult().getFieldError();
        ResponseDto responseDTO = ResponseDto.builder()
            .status(status.toString())
            .message(fieldError.getDefaultMessage()).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        ResponseDto responseDTO = ResponseDto.builder()
            .status(HttpStatus.BAD_REQUEST.toString())
            .message(ex.getMessage()).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }
}
