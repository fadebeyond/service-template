package com.sugarfit.servicetemplate.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sugarfit.servicetemplate.enums.ResponseStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final ResponseStatusEnum status;
    private final String requestId;
    private final T data;
    private final ApiError error;

    private ApiResponse(ResponseStatusEnum status, String requestId, T data, ApiError error) {
        this.status = status;
        this.requestId = requestId;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(String requestId, T data) {
        return new ApiResponse<>(ResponseStatusEnum.SUCCESS, requestId, data, null);
    }

    public static <T> ApiResponse<T> failure(String requestId, ApiError error) {
        return new ApiResponse<>(ResponseStatusEnum.FAILURE, requestId, null, error);
    }

    public ResponseStatusEnum getStatus() {
        return status;
    }

    public String getRequestId() {
        return requestId;
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
