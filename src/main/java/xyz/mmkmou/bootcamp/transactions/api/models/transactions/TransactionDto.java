package xyz.mmkmou.bootcamp.transactions.api.models.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    @NotBlank
    private String ref;

    @NotNull
    private Long fref;

    @NotNull
    @Positive
    private int amount;

    @NotNull
    @Positive
    private Double balance;

    @NotNull
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    @JsonProperty("payment_date")
    private OffsetDateTime paymentDate;

    @NotBlank
    @JsonProperty("phone")
    private String phoneNumber;

    @NotNull
    @JsonProperty("transaction_type")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String transactionType;


    @JsonProperty("transaction_source")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String transactionSource;

    @NotNull
    private boolean isPrinted;

    @NotBlank
    @JsonProperty("from")
    private String fromPhoneNumber;

    @JsonProperty("transaction_currency")
    private String transactionCurrency;

}

