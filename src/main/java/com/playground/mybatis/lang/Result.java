package com.playground.mybatis.lang;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    public enum Status {
        SUCCESS, FAILURE
    }

    private Status status;
    private T payload;

    public static Result<Void> ofDefaultSuccess() {
        return Result.<Void>builder()
                .status(Status.SUCCESS)
                .build();
    }

    public static <T> Result<T> ofSuccess(T payload) {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .payload(payload)
                .build();
    }

    public static Result<Error> ofFailure(String errorMessage, String errorDetails) {
        return Result.<Error>builder()
                .status(Status.FAILURE)
                .payload(Error.builder()
                        .errorMessage(errorMessage)
                        .errorDetails(errorDetails)
                        .build())
                .build();
    }

    @Data
    @Builder
    public static class Error {
        private String errorMessage;
        private String errorDetails;
    }
}
