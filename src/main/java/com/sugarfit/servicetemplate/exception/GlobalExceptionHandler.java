package com.sugarfit.servicetemplate.exception;

import com.sugarfit.servicetemplate.constants.ErrorConstants;
import com.sugarfit.servicetemplate.constants.GlobalConstants;
import com.sugarfit.servicetemplate.model.common.ApiError;
import com.sugarfit.servicetemplate.model.common.ApiResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String requestId() {
        return MDC.get(GlobalConstants.REQUEST_ID);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> details = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.put(error.getField(), error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(
                ErrorConstants.INVALID_REQUEST,
                ErrorConstants.REQUEST_VALIDATION_FAILURE,
                details
        );

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.failure(requestId(), apiError));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex
    ) {
        ApiError apiError = new ApiError(
                ErrorConstants.INVALID_REQUEST,
                ErrorConstants.INCORRECT_REQUEST_BODY
        );

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.failure(requestId(), apiError));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        ApiError apiError = new ApiError(
                ex.getCode(),
                ex.getMessage()
        );

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.failure(requestId(), apiError));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {

        ApiError apiError = new ApiError(
        ErrorConstants.INTERNAL_ERROR, ErrorConstants.SOMETHING_WENT_WRONG
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(requestId(), apiError));
    }
}
