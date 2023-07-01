package com.lina.frostybytes.core_api.shared;

import com.lina.frostybytes.problemdetails.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
public class FrostyBytesException extends RuntimeException  {

    /**
     * human-readable exception title
     */
    @NotBlank
    private final String title;
    /**
     * Error code.
     * Provides information concerning how to handle the error,
     * file name for error documentation and
     * code for translation purposes in the front end
     */
    @NotNull
    private final ErrorCode errorCode;
    /**
     * human-readable detailed exception message
     */
    @Nullable
    private final String detail;


    public FrostyBytesException(@NotBlank String title,
                                @NotNull ErrorCode errorCode) {
        this(title, errorCode, null);
    }

    public FrostyBytesException(@NotBlank String title,
                                @NotNull ErrorCode errorCode,
                                @Nullable String detail) {
        super(detail == null? String.format("%s", title) :
                String.format("%s %n %s", title,detail));
        this.title = title;
        this.errorCode = errorCode;
        this.detail = detail;

    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}


