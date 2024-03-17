package xyz.mmkmou.bootcamp.transactions.common.api.restful.response;


import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;

@Getter
public class ErrorResponse extends BaseResponse {

    private ArrayList<String> errors;

    @Builder
    public ErrorResponse(@NotBlank String code, @NotBlank String message, @NotBlank ArrayList<String> errors) {
        super(code, message);
        this.errors = errors;
    }
}
