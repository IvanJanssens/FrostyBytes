package com.lina.frostybytes.problemdetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GlobalErrorCode.class, name = "GlobalErrorCode")
})
public interface ErrorCode {
    String getFileName();

    Boolean getIsDomainCode();

    ErrorHandlingCode getErrorHandlingCode();
    default HttpStatus getHttpStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
