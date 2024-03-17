package xyz.mmkmou.bootcamp.transactions.api.models.transactions;


import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class TransactionListResponse extends BaseResponse {
    private List<TransactionDto> data;


    @Builder
    public TransactionListResponse(@NotBlank String code, @NotBlank String message, List<TransactionDto> data) {
        super(code, message);
        this.data = data;
    }
}
