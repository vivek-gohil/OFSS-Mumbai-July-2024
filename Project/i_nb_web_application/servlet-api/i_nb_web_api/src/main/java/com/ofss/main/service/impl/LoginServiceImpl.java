package com.ofss.main.service.impl;

import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.repository.CustomerRepository;
import com.ofss.main.repository.LoginRepository;
import com.ofss.main.repository.impl.CustomerRepositoryImpl;
import com.ofss.main.repository.impl.LoginRepositoryImpl;
import com.ofss.main.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private LoginRepository loginRepository = new LoginRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Login createNewLogin(Login login) {
        int loginId = loginRepository.addNewLogin(login);
        if (loginId != 0) {
            login.setLoginId(loginId);
            return login;
        }
        return null;
    }

    // return flags 
    // loginStatus = NEW => -1
    // loginStatus = LOCKED => -2
    // loginAttempts > 3 = -3
    // INVALID PASSWORD = CountOfLoginAttempts
    // INVALID CUSTOMERID = -4
    // ALL VALID DETAILS = 1
    @Override
    public int validateLogin(int customerId, String password) {
        Customer customer = customerRepository.getCustomerByCustomerId(customerId);
        //System.out.println(customer);
        if (customer != null) {
            Login login = loginRepository.getLoginByCustomerId(customer.getCustomerId());
            System.out.println(login);
            System.out.println("Input Password :: " + password );
            switch (login.getLoginStatus()) {
                case "NEW":
                    return -1;
                case "LOCKED":
                    return -2;
                case "ACTIVE":
                    if (login.getLoginAttempts() < 3) {
                        if (login.getPassword().equals(password)) {
                            return 0;
                        } else {
                            loginRepository.incrementLoginAttemptsByLoginId(login.getLoginId());
                            return login.getLoginAttempts();
                        }
                    } else {
                        return -3;
                    }
                default:
                    return -4;
            }
        } else {
            return -4;
        }

    }

}
