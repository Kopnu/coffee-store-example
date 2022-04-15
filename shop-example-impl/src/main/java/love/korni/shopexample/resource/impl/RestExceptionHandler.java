/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.resource.impl;

import love.korni.shopexample.dto.ErrorResponse;
import love.korni.shopexample.exception.EntityNotFoundException;
import love.korni.shopexample.exception.ValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.time.OffsetDateTime;

/**
 * Exception handler.
 *
 * @author Sergei_Konilov
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest req) {
        return errorResponse(ex, req.getRequestURL().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest req) {
        return errorResponse(ex, req.getRequestURL().toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, HttpServletRequest req) {
        return errorResponse(ex, req.getRequestURL().toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> errorResponse(Exception e, String path, HttpStatus httpStatus) {
        String message = tryResolveMessageKey(e.getMessage());
        ErrorResponse error = new ErrorResponse(httpStatus.getReasonPhrase(), message, path, httpStatus.value(), OffsetDateTime.now());
        log.error("{}", message);
        return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
    }

    private String tryResolveMessageKey(String message) {
        try {
            return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return message;
        }
    }

}
