package com.ofss.main.service;

import com.ofss.main.domain.Login;

public interface LoginService {
    Login createNewLogin(Login login);
    int validateLogin(int customerId, String password);
}
