package com.ofss.main.service.impl;

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

}
