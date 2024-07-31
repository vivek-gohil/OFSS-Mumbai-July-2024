package com.ofss.main.repository;

import com.ofss.main.domain.Login;

public interface LoginRepository {
    int addNewLogin(Login login);
    Login getLoginByCustomerId(int customerId);
    boolean incrementLoginAttemptsByLoginId(int loginId);
    boolean updateLoginStatusByLoginId(int loginId);
}
