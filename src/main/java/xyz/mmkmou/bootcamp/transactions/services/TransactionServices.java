package xyz.mmkmou.bootcamp.transactions.services;


import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionDto;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionListResponse;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionResponse;

public interface TransactionServices {
    TransactionResponse createTransaction(TransactionDto transactionDto);

    TransactionListResponse getAllTransaction();

    TransactionListResponse getTransactionByFref(String fref);

    TransactionResponse validateTransaction(String fref, String amount, String phone);

    TransactionResponse updateTransaction(String fref, String amount, String phone, TransactionDto transactionDto);
}
