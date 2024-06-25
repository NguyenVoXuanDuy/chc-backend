package com.xuanduy.chc_backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    FIELD_NOT_FOUND(1002, "{field} not found", HttpStatus.BAD_REQUEST),
    FIELD_EXISTED(1003, "{field} already existed", HttpStatus.BAD_REQUEST),
    MANDATORY_FIELD(1013, "{field} can not be null or blank", HttpStatus.BAD_REQUEST),
    INVALID_SIZE(1014, "", HttpStatus.BAD_REQUEST),
    INVALID_FIELD(1015, "{field} is invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1008, "Invalid token", HttpStatus.BAD_REQUEST),
    EXPIRED_TOKEN(1009, "Expired token", HttpStatus.BAD_REQUEST),
    IMAGE_REQUIRED(1010, "Image is required", HttpStatus.BAD_REQUEST),
    STATUS_INVALID(1011, "Status must be PENDING, CONFIRMED, CANCELLED, COMPLETED", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1012, "Role not found", HttpStatus.BAD_REQUEST),
    BAD_CREDENTIALS(1016, "Username or password is incorrect", HttpStatus.UNAUTHORIZED),
    USER_EXISTED(1017, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1018, "User not found", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1019, "Role already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1020, "Product not found", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1021, "Product already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1022, "Category not found", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND(1023, "Brand not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1024, "Order not found", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND(1025, "Image not found", HttpStatus.BAD_REQUEST),
    IMAGE_EXISTED(1026, "Image already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED(1027, "Category already existed", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1028, "Cart not found", HttpStatus.BAD_REQUEST),
    BRAND_EXISTED(1029, "Brand already existed", HttpStatus.BAD_REQUEST),
    ;
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }


}