package xyz.mmkmou.bootcamp.transactions.common.api.mappers;


import xyz.mmkmou.bootcamp.transactions.api.models.transactions.SourceEnum;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class EnumMapper {
    public TransactionTypeEnum asTransactionTypeEnum(String str) {
        return TransactionTypeEnum.valueOfLabel(str);
    }

    public SourceEnum asSourceEnum(String str) {

        return SourceEnum.valueOfLabel(str);
    }

    public String asTransactionTypeEnumString(xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionTypeEnum typeEnum) {
        return typeEnum.getLabel();
    }

    public String asSourceEnumString(xyz.mmkmou.bootcamp.transactions.api.models.transactions.SourceEnum sourceEnum) {
        return sourceEnum.getLabel();
    }
}
