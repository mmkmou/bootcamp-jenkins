package xyz.mmkmou.bootcamp.transactions.services.impl;

import xyz.mmkmou.bootcamp.transactions.persistence.models.Transaction;
import xyz.mmkmou.bootcamp.transactions.persistence.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionDto;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionListResponse;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionMapper;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionResponse;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.ResponseCode;
import xyz.mmkmou.bootcamp.transactions.services.TransactionServices;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServicesImpl implements TransactionServices {

    final TransactionRepository transactionRepository;
    final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse createTransaction(TransactionDto transactionDto) {
        TransactionResponse response = null;
        Transaction transaction = null;

        try {
             transaction = transactionRepository.save(
                    transactionMapper.transactionDtoToTransaction(transactionDto)
            );

        } catch (DataAccessException dae) {
            response = TransactionResponse.builder()
                            .code(ResponseCode.BAD_REQUEST.getCode())
                            .message(dae.getRootCause().getMessage())
                            .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = TransactionResponse.builder()
                            .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getCause().getMessage())
                            .build();
        } finally {
            if (!(transaction == null)) {
                response = TransactionResponse.builder()
                                .code("00")
                                .message("Operation OK")
                                .data(transactionMapper.transactionToTransactionDto(transaction))
                                .build();
            }
        }
        return response;
    }

    @Override
    public TransactionListResponse getAllTransaction() {
        TransactionListResponse response = null;
        Transaction transaction = null;
        List<Transaction> transactions = null;
        try {
            transactions = transactionRepository.findAll();
        } catch (DataAccessException dae) {
            response = TransactionListResponse.builder()
                    .code(ResponseCode.BAD_REQUEST.getCode())
                    .message(dae.getRootCause().getMessage())
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = TransactionListResponse.builder()
                    .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getCause().getMessage())
                    .build();
        } finally {
            if (!(null == transactions)) {
                response = TransactionListResponse.builder()
                                .code("00")
                                .message("Operation OK")
                                .data(transactions
                                        .stream()
                                        .map(transaction1 -> transactionMapper.transactionToTransactionDto(transaction1))
                                        .collect(Collectors.toList())
                                )
                                .build();
            }
        }
        return response;
    }

    @Override
    public TransactionListResponse getTransactionByFref(String fref) {
        TransactionListResponse response = null;
        List<Transaction> transactions = null;
        try {
            if (fref.length() == 15) {
                transactions = transactionRepository.findByFref(Long.valueOf(fref));
            } else {
                String exceptionMessage = "Fref size is not correct : " + fref.length() + " should be 15.";
//                throw new Exception(exceptionMessage);
                response = TransactionListResponse.builder()
                        .code(ResponseCode.BAD_REQUEST.getCode())
                        .message(exceptionMessage)
                        .build();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            response = TransactionListResponse.builder()
                    .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getCause().getMessage())
                    .build();
        } finally {
            if (!(null == transactions)) {
                response = TransactionListResponse.builder()
                        .code("00")
                        .message("Operation OK")
                        .data(transactions
                                .stream()
                                .map(transaction1 -> transactionMapper.transactionToTransactionDto(transaction1))
                                .collect(Collectors.toList())
                        )
                        .build();
            }
        }

        return response;
    }

    @Override
    public TransactionResponse validateTransaction(String fref, String amount, String phone) {
        TransactionResponse response = null;
        Transaction transaction = null;
        try {
            if (fref.length() == 15) {
                transaction = transactionRepository.findFirstByFrefAndAmountAndPhoneNumber(Long.valueOf(fref), Integer.valueOf(amount), phone);

                if (transaction == null) {
                    throw new NotFoundException("Transaction not found");
                }

            } else {
                String exceptionMessage = "Fref size is not correct : " + fref.length() + " should be 15.";
                response = TransactionResponse.builder()
                                .code(ResponseCode.BAD_REQUEST.getCode())
                                .message(exceptionMessage)
                                .build();
            }
        } catch (NotFoundException nfe) {

            System.out.println(nfe.getMessage());
            response = TransactionResponse.builder()
                    .code(ResponseCode.NOT_FOUND.getCode())
                    .message(nfe.getMessage())
                    .build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            response = TransactionResponse.builder()
                            .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getCause().getMessage())
                            .build();
        } finally {
            if (!(null == transaction)) {
                response = TransactionResponse.builder()
                                .code("00")
                                .message("Operation OK")
                                .data(transactionMapper.transactionToTransactionDto(transaction))
                                .build();
            }
        }

        return response;
    }

    @Override
    public TransactionResponse updateTransaction(String fref, String amount, String phone, TransactionDto transactionDto) {
        TransactionResponse response = null;
        Transaction transaction = null;
        try {
            transaction = transactionRepository.findFirstByFrefAndAmountAndPhoneNumber(Long.valueOf(fref), Integer.valueOf(amount), phone);
            if (transaction == null) {
                throw new NotFoundException("Transaction not found");
            }
            transactionMapper.updateTransactionFromDto(transactionDto, transaction);
            transactionRepository.save(transaction);
        } catch (NotFoundException nfe) {
            System.out.println(nfe.getMessage());
            response = TransactionResponse.builder()
                        .code(ResponseCode.NOT_FOUND.getCode())
                        .message(nfe.getCause().getMessage())
                        .build();
        } catch (DataAccessException dae) {
            response = TransactionResponse.builder()
                    .code(ResponseCode.BAD_REQUEST.getCode())
                    .message(dae.getRootCause().getMessage())
                    .build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            response = TransactionResponse.builder()
                    .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getCause().getMessage())
                    .build();

        } finally {
            if (!(transaction == null)) {
                response = TransactionResponse.builder()
                        .code("00")
                        .message("Operation OK")
                        .data(transactionMapper.transactionToTransactionDto(transaction))
                        .build();
            }
        }

        return response;
    }
}
