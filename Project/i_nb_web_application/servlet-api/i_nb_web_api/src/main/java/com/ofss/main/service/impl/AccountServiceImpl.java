package com.ofss.main.service.impl;

import java.util.List;

import com.ofss.main.domain.Account;
import com.ofss.main.repository.AccountRepository;
import com.ofss.main.repository.impl.AccountRepositoryImpl;
import com.ofss.main.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository = new AccountRepositoryImpl();
    
    @Override
    public int addNewAccount(Account account) {
       return accountRepository.addNewAccount(account);
    }

    @Override
    public List<Account> getAllAccountsByCustomerId(int customerId) {
       return accountRepository.getAllAccountsByCustomerId(customerId);
    }

}
