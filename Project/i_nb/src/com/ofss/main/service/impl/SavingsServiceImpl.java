package com.ofss.main.service.impl;

import com.ofss.main.domain.Account;
import com.ofss.main.domain.Savings;
import com.ofss.main.repository.AccountRepository;
import com.ofss.main.repository.SavingsRepository;
import com.ofss.main.repository.impl.AccountRepositoryImpl;
import com.ofss.main.repository.impl.SavingsRepositoryImpl;
import com.ofss.main.service.SavingsService;

public class SavingsServiceImpl implements SavingsService {

    private SavingsRepository savingsRepository = new SavingsRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public boolean addNewSavingsAccount(Savings savings) {
        Account account = new Account(savings.getCustomer(), "SAVINGS");
        int accountId = accountRepository.addNewAccount(account);
        savings.setAccountId(accountId);
        return savingsRepository.addNewSavingsAccount(savings);
    }

}
