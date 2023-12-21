package com.banking.Service;

import com.banking.Dto.TransactionDto;
import com.banking.Model.Account;
import com.banking.Model.Transaction;
import com.banking.Repository.AccountRepository;
import com.banking.Repository.TransactionRepository;
import com.banking.Repository.TransactionalTypeRepository;
import com.banking.Request.TransactionRequest;
import com.banking.Response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionalTypeRepository transactionalTypeRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionDto transactionDto;

    public List<TransactionResponse> updateCredit(TransactionRequest transactionRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(transactionRequest.getUserId());

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            int balance = account.getBalance();

            Transaction transaction = new Transaction();

            if (transactionRequest.getAmount() > 0) {
                int newBalance = balance + transactionRequest.getAmount();
                account.setBalance(newBalance);
                transaction.setCurrentBalance(newBalance);
                transaction.setUserId(accountRepository.findByTableId(transactionRequest.getUserId()));
                transaction.setAmount(transactionRequest.getAmount());
                transaction.setType(transactionalTypeRepository.findById(transactionRequest.getTransactionalType()));
            }

            accountRepository.save(account);
            transactionRepository.save(transaction);
            List<Transaction> transactionList = transactionRepository.findTransactionByUserId(transactionRequest.getUserId());
            return transactionDto.mapToResponse(transactionList);
        } else {
            return Collections.emptyList();
        }
    }

    public List<TransactionResponse> updateDebit(TransactionRequest transactionRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(transactionRequest.getUserId());

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            int balance = account.getBalance();

            Transaction transaction = new Transaction();

            if (transactionRequest.getAmount() < account.getBalance()) {
                int newBalance = balance - transactionRequest.getAmount();
                account.setBalance(newBalance);
                transaction.setCurrentBalance(newBalance);
                transaction.setUserId(accountRepository.findByTableId(transactionRequest.getUserId()));
                transaction.setAmount(transactionRequest.getAmount());
                transaction.setType(transactionalTypeRepository.findById(transactionRequest.getTransactionalType()));
            }

            accountRepository.save(account);
            transactionRepository.save(transaction);
            List<Transaction> transactionList = transactionRepository.findTransactionByUserId(transactionRequest.getUserId());
            return transactionDto.mapToResponse(transactionList);
        } else {
            return Collections.emptyList();
        }

    }
}

