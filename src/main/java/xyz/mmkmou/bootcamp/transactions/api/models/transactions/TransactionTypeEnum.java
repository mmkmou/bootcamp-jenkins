package xyz.mmkmou.bootcamp.transactions.api.models.transactions;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum TransactionTypeEnum {
    PAYMENT("paiement"),
    RECEIPT("reception"),
    TRANSFER("transfert");

    private static final Map<String, TransactionTypeEnum> lookup = new HashMap<>();

    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }

    public static TransactionTypeEnum valueOfLabel(String label) {
        return lookup.get(label);
    }
    static
    {
        for(TransactionTypeEnum e : values())
        {
            lookup.put(e.getLabel(), e);
        }
    }
}