package com.ofss.main.repository;

import java.util.List;

import com.ofss.main.domain.Account;

public interface AccountRepository {
    int addNewAccount(Account account);
    List<Account> getAllAccountsByCustomerId(int customerId);
}
