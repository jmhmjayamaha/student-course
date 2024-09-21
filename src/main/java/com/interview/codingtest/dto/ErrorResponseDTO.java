package com.interview.codingtest.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDTO {

    @Schema(description = "API path invoked by client")
    private String apiPath;

    @Schema(description = "Error response code")
    private int errorCode;

    @Schema(description = "Error Message")
    private String errorMessage;

    @Schema(description = "Time when the error happend")
    private LocalDateTime errorTime;
}
