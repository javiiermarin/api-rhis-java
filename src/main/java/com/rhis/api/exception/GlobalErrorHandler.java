package com.rhis.api.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception personalizada para InternalServerError
     *
     * @param ex  de tipo Excepcion
     * @param req de tipo WebRequest
     * @return Excepcion personalizada 500 con fecha y hora actual, status code, mensaje personalizado y url
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Excepcion personalizada para AccessDeniedException
     *
     * @param ex  de tipo Excepcion
     * @param req de tipo WebRequest
     * @return Excepcion personalizada 403 con fecha y hora actual, status code, mensaje personalizado y url
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Exception personalizada para NotFound
     *
     * @param notFoundException de tipo NotFoundException clase personalizada 404
     * @param req               req de tipo WebRequest
     * @return Excepcion personalizada 404 con Fecha y hora actual, status code, mensaje personalizado y url
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException notFoundException, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), notFoundException.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntityException(UnprocessableEntityException unprocessableEntityException, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), unprocessableEntityException.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Exception personalizada para BadRequest
     *
     * @param badRequestException de tipo BadRequestException clase personalizada 400
     * @param req                 req de tipo WebRequest
     * @return Excepcion personalizada 400 con Fecha y hora actual, status code,  mensaje personalizado y url
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Excepcion personalizada para validar los campos de la entidad
     *
     * @param ex      de tipo MethodArgumentNotValidException clase personalizada 400
     * @param headers los encabezados que se escribirán en la respuesta
     * @param status  el estado de respuesta seleccionado
     * @param req     la solicitud actual
     * @return Excepcion personalizada 400 indicando los problemas de la solicitud con Fecha y hora actual, status code,  mensaje personalizado y url
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, HttpStatusCode status, @NotNull WebRequest req) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField().toUpperCase() + ": " + error.getDefaultMessage() + ".")
                .collect(Collectors.joining(" "));

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), status.value(), message, req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
