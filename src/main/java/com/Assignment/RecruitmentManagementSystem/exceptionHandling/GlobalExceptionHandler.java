package com.Assignment.RecruitmentManagementSystem.exceptionHandling;

import com.Assignment.RecruitmentManagementSystem.exceptionHandling.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistExceptionHandler(UserAlreadyExistException ex){

        ErrorResponse error=new ErrorResponse(ex.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT);

        return ResponseEntity.status(409).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ErrorResponse error=new ErrorResponse(message,LocalDateTime.now(),HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(400).body(error);
    }
}
