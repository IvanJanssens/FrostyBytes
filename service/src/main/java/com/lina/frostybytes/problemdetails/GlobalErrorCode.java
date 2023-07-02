package com.lina.frostybytes.problemdetails;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalErrorCode implements ErrorCode {

    ENTITY_NOT_FOUND_ERROR(null, ErrorHandlingCode.BUSINESS_ERROR, HttpStatus.NOT_FOUND),
    FILE_NOT_FOUND_ERROR(null, ErrorHandlingCode.HUMAN_INTERVENTION, HttpStatus.NOT_FOUND),
    EXTERNAL_SYSTEM(null, ErrorHandlingCode.RETRY, HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN(null, ErrorHandlingCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR),
    CONFIGURATION(null, ErrorHandlingCode.HUMAN_INTERVENTION, HttpStatus.INTERNAL_SERVER_ERROR),
    JSON(null, ErrorHandlingCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR),
    RETRY(null, ErrorHandlingCode.RETRY, HttpStatus.INTERNAL_SERVER_ERROR),
    MAPPER_ERROR(null, ErrorHandlingCode.HUMAN_INTERVENTION, HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_RETRY(null, ErrorHandlingCode.DEAD_LETTER_QUEUE, HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(null, ErrorHandlingCode.BUSINESS_ERROR, HttpStatus.BAD_REQUEST),
    BUSINESS_EXCEPTION(null, ErrorHandlingCode.BUSINESS_ERROR, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(null, ErrorHandlingCode.BUSINESS_ERROR, HttpStatus.UNAUTHORIZED),
    FORBIDDEN(null, ErrorHandlingCode.BUSINESS_ERROR, HttpStatus.FORBIDDEN);

    private final String fileName;
    private final ErrorHandlingCode errorHandlingCode;
    private final HttpStatus httpStatus;

    GlobalErrorCode(String fileName, ErrorHandlingCode errorHandlingCode, HttpStatus httpStatus) {
        this.fileName = fileName;
        this.errorHandlingCode = errorHandlingCode;
        this.httpStatus = httpStatus;
    }

    @Override
    public Boolean getIsDomainCode() {
        return false;
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return httpStatus;
    }
}
