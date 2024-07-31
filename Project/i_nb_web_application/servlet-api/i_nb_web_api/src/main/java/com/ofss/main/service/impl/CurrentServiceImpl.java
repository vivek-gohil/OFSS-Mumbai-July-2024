package com.ofss.main.service.impl;

import com.ofss.main.domain.Account;
import com.ofss.main.domain.Current;
import com.ofss.main.repository.AccountRepository;
import com.ofss.main.repository.CurrentRepository;
import com.ofss.main.repository.impl.AccountRepositoryImpl;
import com.ofss.main.repository.impl.CurrentRepositoryImpl;
import com.ofss.main.service.CurrentService;

public class CurrentServiceImpl implements CurrentService {

    private CurrentRepository currentRepository = new CurrentRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();


    @Override
    public boolean addNewCurrentAccount(Current current) {
        Account account = new Account(current.getCustomer(), "CURRENT");
        int accountId = accountRepository.addNewAccount(account);
        current.setAccountId(accountId);
        return  currentRepository.addNewCurrentAccount(current); 
    }

}
