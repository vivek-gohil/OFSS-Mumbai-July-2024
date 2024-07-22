package com.ofss.main.service.impl;

import com.ofss.main.domain.Login;
import com.ofss.main.repository.LoginRepository;
import com.ofss.main.repository.impl.LoginRepositoryImpl;
import com.ofss.main.service.LoginService;

public class LoginServiceImpl implements  LoginService {

    private LoginRepository loginRepository = new LoginRepositoryImpl();

    @Override
    public Login createNewLogin(Login login) {
        int loginId = loginRepository.addNewLogin(login);
        if(loginId != 0){
            login.setLoginId(loginId);
            return login;
        }
        return  null;
    }

}
