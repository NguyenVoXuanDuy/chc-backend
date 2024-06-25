package com.xuanduy.chc_backend.exception;


import com.xuanduy.chc_backend.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        String message = generateMessage(null, errorCode, exception.getFieldName());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(capitalizeFirstCharacter(message))
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var constraintViolation =
                e.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
        Map attributes = constraintViolation.getConstraintDescriptor().getAttributes();
        var error = (FieldError) e.getBindingResult().getAllErrors().getFirst();
        String fieldName = error.getField();
        if (attributes.containsKey("errorCode")) {
            ErrorCode errorCode = (ErrorCode) attributes.get("errorCode");
            String message = generateMessage(attributes, errorCode, fieldName);
            return ResponseEntity
                    .status(errorCode.getStatusCode())
                    .body(ApiResponse.builder()
                            .code(errorCode.getCode())
                            .message(capitalizeFirstCharacter(message))
                            .build());
        }
        return ResponseEntity
                .status(ErrorCode.INVALID_KEY.getStatusCode())
                .body(ApiResponse.builder()
                        .code(ErrorCode.INVALID_KEY.getCode())
                        .message(ErrorCode.INVALID_KEY.getMessage())
                        .build());
    }

    private String generateMessage(Map attributes, ErrorCode errorCode, String fieldName) {
        String message = errorCode.getMessage();
        message = message.replace("{field}", fieldName);
        if (errorCode.getCode() == 1014) {
            message = fieldName + " length ";
            if ((int) attributes.get("min") == -1 && (int) attributes.get("max") != -1) {
                message += "must be less than or equal to " + attributes.get("max");
            } else if ((int) attributes.get("min") != -1 && (int) attributes.get("max") == -1) {
                message += "must be greater than or equal to " + attributes.get("min");
            } else {
                message += "must be between " + attributes.get("min") + " and " + attributes.get("max");
            }
            return message;
        }

        return message;
    }

    private static String capitalizeFirstCharacter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Access denied", e);
        System.out.println(e.toString());
        return ResponseEntity
                .status(ErrorCode.UNAUTHORIZED.getStatusCode())
                .body(ApiResponse.builder()
                        .code(ErrorCode.UNAUTHORIZED.getCode())
                        .message(ErrorCode.UNAUTHORIZED.getMessage())
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleException() {
        return ResponseEntity
                .status(ErrorCode.BAD_CREDENTIALS.getStatusCode())
                .body(
                        ApiResponse.builder()
                                .code(ErrorCode.BAD_CREDENTIALS.getCode())
                                .message(ErrorCode.BAD_CREDENTIALS.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponse.builder()
                                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                                .build()
                );
    }
    
}
