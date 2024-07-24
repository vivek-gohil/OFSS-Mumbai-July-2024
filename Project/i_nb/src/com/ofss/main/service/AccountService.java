package com.ofss.main.service;

import java.util.List;

import com.ofss.main.domain.Account;

public interface AccountService {
int addNewAccount(Account account);
List<Account> getAllAccountsByCustomerId(int customerId);
}
