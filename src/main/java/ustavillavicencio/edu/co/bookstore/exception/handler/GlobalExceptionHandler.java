package ustavillavicencio.edu.co.bookstore.exception.handler;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ustavillavicencio.edu.co.bookstore.dto.response.ApiErrorResponse;
import ustavillavicencio.edu.co.bookstore.exception.custom.AuthorHasBooksException;
import ustavillavicencio.edu.co.bookstore.exception.custom.DuplicateResourceException;
import ustavillavicencio.edu.co.bookstore.exception.custom.InsufficientStockException;
import ustavillavicencio.edu.co.bookstore.exception.custom.InvalidOrderStateException;
import ustavillavicencio.edu.co.bookstore.exception.custom.ResourceNotFoundException;
import ustavillavicencio.edu.co.bookstore.exception.custom.UnauthorizedAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleAllExceptions(Exception ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Error interno del servidor");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(500);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Recurso no encontrado");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(404);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ApiErrorResponse handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Recurso duplicado");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(409);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ApiErrorResponse handleInsufficientStockException(InsufficientStockException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Stock insuficiente");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(422);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(InvalidOrderStateException.class)
    public ApiErrorResponse handleInvalidOrderStateException(InvalidOrderStateException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Estado de orden inválido");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(409);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }


    @ExceptionHandler(UnauthorizedAccessException.class)
    public ApiErrorResponse handleUnauthorizedAccessException(UnauthorizedAccessException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Acceso no autorizado");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(403);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(AuthorHasBooksException.class)
    public ApiErrorResponse handleAuthorHasBooksException(AuthorHasBooksException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("El autor tiene libros asociados");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(409);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Error de validación" + ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", ")));
        response.setErrors(ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
        response.setErrorCode(422);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ApiErrorResponse handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Acceso denegado");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(403);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }


    @ExceptionHandler(AuthenticationException.class)
    public ApiErrorResponse handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setSuccess(false);
        response.setMessage("Autenticación requerida");
        response.setErrors(Arrays.asList(ex.getMessage()));
        response.setErrorCode(401);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(request.getRequestURI());
        return response;
    }

    
}