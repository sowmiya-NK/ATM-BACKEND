package com.banking.Dto;

import com.banking.Model.Transaction;
import com.banking.Model.TransactionalType;
import com.banking.Repository.AccountRepository;
import com.banking.Repository.TransactionRepository;
import com.banking.Repository.TransactionalTypeRepository;
import com.banking.Request.TransactionRequest;
import com.banking.Response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionDto {

    @Autowired
    private TransactionalTypeRepository transactionalTypeRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction mapToCredit(Transaction transactionRow) {
        Transaction transaction = new Transaction();
        transaction.setCurrentBalance(transactionRow.getCurrentBalance());
        transaction.setAmount(transactionRow.getAmount());
        transaction.setType(transactionRow.getType());
        transaction.setUserId(transactionRow.getUserId());

        return transaction;
    }

    public List<TransactionResponse> mapToResponse(List<Transaction> transaction) {
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for (Transaction transaction1 : transaction) {
            TransactionResponse t = new TransactionResponse();
            t.setUserId(transaction1.getUserId().getId());
            t.setAmount(transaction1.getAmount());
            t.setCurrentBalance(transaction1.getCurrentBalance());

            t.setTransactionalType(transaction1.getType());
            transactionResponseList.add(t);
        }


        return transactionResponseList;
    }


}
