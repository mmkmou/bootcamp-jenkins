package xyz.mmkmou.bootcamp.transactions.api.models.transactions;


import jakarta.validation.constraints.NotBlank;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.BaseResponse;
import lombok.*;





@Getter
public class TransactionResponse extends BaseResponse {

    private TransactionDto data;

    @Builder

    public TransactionResponse(@NotBlank String code, @NotBlank String message, TransactionDto data) {
        super(code, message);
        this.data = data;
    }
}
