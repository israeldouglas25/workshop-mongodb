package apiworkshopmongodb.com.br.workshop.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception ex, HttpServletRequest request) {
        String message;

        if (ex instanceof HttpMessageNotReadableException) {
            message = "Required request body is missing or malformed JSON.";
        } else if (ex instanceof MissingServletRequestParameterException msp) {
            message = "Missing required request parameter: " + msp.getParameterName();
        } else {
            message = "Bad Request.";
        }

        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Not Found", request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String supported = ex.getSupportedHttpMethods() != null ? ex.getSupportedHttpMethods().toString() : "N/A";
        String message = "Method " + ex.getMethod() + " not allowed. Supported: " + supported;
        return buildErrorResponse(ex, HttpStatus.METHOD_NOT_ALLOWED, message, request);
    }

    @ExceptionHandler(Exception.class) // fallback para erros inesperados
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status, String error, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                Instant.now(),
                status.value(),
                error,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }
}

