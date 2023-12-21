package com.banking.Response;

import com.banking.Model.TransactionalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private int userId;
    private int amount;
    private int currentBalance;
    private Date date;
    private TransactionalType transactionalType;

}
