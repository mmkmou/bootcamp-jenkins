package xyz.mmkmou.bootcamp.transactions.api.models.transactions;




import xyz.mmkmou.bootcamp.transactions.common.api.mappers.BaseMapperConfig;
import xyz.mmkmou.bootcamp.transactions.common.api.mappers.DateMapper;
import xyz.mmkmou.bootcamp.transactions.common.api.mappers.EnumMapper;
import xyz.mmkmou.bootcamp.transactions.persistence.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        config = BaseMapperConfig.class,
        uses = {DateMapper.class, EnumMapper.class}
)
public interface TransactionMapper {

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    TransactionDto transactionToTransactionDto(Transaction transaction);

    void updateTransactionFromDto(TransactionDto dto, @MappingTarget Transaction entity);

}
