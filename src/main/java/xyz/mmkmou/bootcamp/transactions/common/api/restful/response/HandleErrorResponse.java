package xyz.mmkmou.bootcamp.transactions.common.api.restful.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HandleErrorResponse {
    private String code;
    private String message;

    public ErrorResponse handleError() {
        return ErrorResponse.builder()
                .code(getCode())
                .message(ResponseCode.valueOfCode(getCode()).getDescription())
                .errors(new ArrayList(
                        Arrays.asList(
                                getMessage().split("\n")
                        )
                ))
                .build();
    }
}
