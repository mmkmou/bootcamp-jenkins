package xyz.mmkmou.bootcamp.transactions.common.api.restful.response;


import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BaseResponse {
    @NotBlank
    private String code;
    @NotBlank
    private String message;


}
