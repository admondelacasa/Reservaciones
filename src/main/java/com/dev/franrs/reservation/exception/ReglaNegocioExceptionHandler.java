package com.dev.franrs.reservation.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReglaNegocioExceptionHandler {

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<ProblemDetail> handle(ReglaNegocioException ex) {
        ProblemDetail detail =
                ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(detail);
    }
}
