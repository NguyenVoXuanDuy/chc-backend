package com.xuanduy.chc_backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String fieldName;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.fieldName = "";
    }

    public AppException(ErrorCode errorCode, String fieldName) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.fieldName = fieldName;
    }


}
