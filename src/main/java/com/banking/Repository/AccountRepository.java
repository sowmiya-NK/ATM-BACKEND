package com.banking.Repository;

import com.banking.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByname(String name);

    @Query("FROM Account acc WHERE acc.id=:id")
    Account findByTableId(int id);
}
