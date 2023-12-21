package com.banking.Request;

import com.banking.Model.Account;
import com.banking.Model.TransactionalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

public class TransactionRequest {

    private int amount;
    private int transactionalType;
    private int userId;


}
