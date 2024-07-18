CREATE DATABASE i_nb; 

USE i_nb;

-- login_details
DROP TABLE login_details;
CREATE TABLE login_details(
    login_id INT AUTO_INCREMENT,
    password VARCHAR(50) NOT NULL,
    login_attempts INT NOT NULL,
    login_status VARCHAR(10) NOT NULL,
    CONSTRAINT login_id_pk PRIMARY KEY(login_id),
    CONSTRAINT login_status_ck CHECK (login_status IN( 'ACTIVE','LOCKED'))
);

INSERT INTO login_details(password,login_attempts,login_status) VALUES ('Bahubali@123',0,'ACTIVE');
INSERT INTO login_details(password,login_attempts,login_status) VALUES ('Changeme@123',0,'ACTIVE');
INSERT INTO login_details(password,login_attempts,login_status) VALUES ('Changeme@123',0,'ACTIVE');
INSERT INTO login_details(password,login_attempts,login_status) VALUES ('Changeme@123',0,'ACTIVE');
INSERT INTO login_details(password,login_attempts,login_status) VALUES ('Changeme@123',0,'ACTIVE');

SELECT * FROM login_details;

-- customer_details
DROP TABLE customer_details;
CREATE TABLE customer_details(
    customer_id INT AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    login_id INT,
    customer_status VARCHAR(10),
    CONSTRAINT customer_id_pk PRIMARY KEY(customer_id),
    CONSTRAINT login_id_fk FOREIGN KEY(login_id) REFERENCES login_details(login_id),
    CONSTRAINT customer_status_ck CHECK( customer_status IN('NEW','APPROVED'))
);

INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status)
VALUES('Vivek','Gohil','Male','ghl_vivek@hotmail.com',9920728158,1,'NEW');
INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status)
VALUES('Samarth','Patil','Male','samarth.patil@gmail.com',9876543212,2,'NEW');
INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status)
VALUES('Trupti','Acharekar','Female','trupti.acharekar@gmail.com',9432113122,3,'NEW');
INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status)
VALUES('Advait','Gohil','Male','advait.gohil@gmail.com',9432113122,4,'NEW');
INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status)
VALUES('Gurubux','Gill','Male','gurubux.gill@hotmail.com',9432113122,5,'NEW');

SELECT * FROM customer_details;

-- account_details
DROP TABLE account_details;

CREATE TABLE account_details(
    account_id INT AUTO_INCREMENT,
    customer_id INT,
    account_type VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL,
    CONSTRAINT account_id_pk PRIMARY KEY(account_id),
    CONSTRAINT customer_id_fk FOREIGN KEY(customer_id) REFERENCES customer_details(customer_id),
    CONSTRAINT account_type_ck CHECK( account_type IN('SAVINGS','CURRENT','FIXED DEPOSIT'))
);

INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(1,'SAVINGS',10000);
INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(2,'CURRENT',10000);
INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(3,'SAVINGS',10000);
INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(4,'CURRENT',10000);
INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(5,'SAVINGS',10000);
INSERT INTO account_details(customer_id,account_type,balance) 
VALUES(5,'FIXED DEPOSIT',50000);
SELECT * FROM account_details; 

-- savings_account_details
DROP TABLE savings_account_details;
CREATE TABLE savings_account_details(
    savings_account_id INT,
    rate_of_interest INT DEFAULT 0.05 NOT NULL,
    minimum_balance INT DEFAULT 1500 NOT NULL,
    CONSTRAINT savings_account_id_pk PRIMARY KEY(savings_account_id),
    CONSTRAINT savings_account_id_fk FOREIGN KEY(savings_account_id) REFERENCES account_details(account_id)
);

INSERT INTO savings_account_details(savings_account_id)
VALUES(1);
INSERT INTO savings_account_details(savings_account_id)
VALUES(3);
INSERT INTO savings_account_details(savings_account_id)
VALUES(5);

SELECT * FROM savings_account_details;

-- current_account_details
CREATE TABLE current_account_details(
    current_account_id INT,
    overdraft_balance INT NOT NULL,
    remaining_overdraft_balance INT NOT NULL,
    minimum_balance INT DEFAULT 0 NOT NULL,
    CONSTRAINT current_account_id PRIMARY KEY(current_account_id),
    CONSTRAINT current_account_id_fk FOREIGN KEY(current_account_id) REFERENCES account_details(account_id)
);

INSERT INTO current_account_details(current_account_id,overdraft_balance,remaining_overdraft_balance)
VALUES(2,50000,50000);
INSERT INTO current_account_details(current_account_id,overdraft_balance,remaining_overdraft_balance)
VALUES(4,50000,50000);


SELECT * FROM current_account_details;

-- fixed deposit_intrest_duration
DROP TABLE fixed_deposit_validity_interest;
CREATE TABLE fixed_deposit_validity_interest(
    fixed_deposit_interest_validity_id INT AUTO_INCREMENT,
    validity_in_months INT NOT NULL, 
    rate_of_interest DOUBLE NOT NULL,
    CONSTRAINT fixed_deposit_interest_validity_id_pk PRIMARY KEY(fixed_deposit_interest_validity_id)
);

INSERT INTO fixed_deposit_validity_interest(validity_in_months,rate_of_interest)
VALUES(12,0.045);
INSERT INTO fixed_deposit_validity_interest(validity_in_months,rate_of_interest)
VALUES(24,0.05);
INSERT INTO fixed_deposit_validity_interest(validity_in_months,rate_of_interest)
VALUES(36,0.055);

SELECT * FROM fixed_deposit_validity_interest;

-- fixed_deposit_details
DROP TABLE fixed_deposit_details;
CREATE TABLE fixed_deposit_details(
    fixed_deposit_id INT,
    amount DOUBLE NOT NULL,
    maturity_amount DOUBLE NOT NULL,
    fixed_deposit_validity_interest_id INT,
    CONSTRAINT amount_ck CHECK(amount > 0),
    CONSTRAINT maturity_amount_ck CHECK(maturity_amount > amount),
    CONSTRAINT fixed_deposit_id_pk PRIMARY KEY(fixed_deposit_id),
    CONSTRAINT fiexd_deposit_id_fk FOREIGN KEY(fixed_deposit_id) REFERENCES account_details(account_id),
    CONSTRAINT fixed_deposit_validity_interest_fk FOREIGN KEY(fixed_deposit_validity_interest_id) REFERENCES fixed_deposit_validity_interest(fixed_deposit_interest_validity_id)
);

INSERT INTO fixed_deposit_details VALUES(5,50000,55000,1);

SELECT * FROM fixed_deposit_details;

-- cheque_details
DROP TABLE cheque_details;
CREATE TABLE cheque_details(
    cheque_id INT AUTO_INCREMENT,
    receiver_name VARCHAR(50),
    cheque_date DATE,
    amount DOUBLE,
    account_id INT NOT NULL,
    cheque_status VARCHAR(50) NOT NULL,
    CONSTRAINT cheque_status_ck CHECK(cheque_status IN ('NEW','ISSUED','DEPOSITED','CLEARED','BOUNCED')),
    CONSTRAINT cheque_amount_ck CHECK(amount > 0),
    CONSTRAINT cheque_id_pk PRIMARY KEY(cheque_id),
    CONSTRAINT account_id_fk FOREIGN KEY(account_id) REFERENCES account_details(account_id)
);

-- bank_slip_details
CREATE TABLE bank_slip_details(
    slip_id INT AUTO_INCREMENT,
    cheque_id INT NOT NULL,
    depositor_account_id INT NOT NULL,
    CONSTRAINT slip_id_pk PRIMARY KEY(slip_id),
    CONSTRAINT cheque_id_fk FOREIGN KEY(cheque_id) REFERENCES cheque_details(cheque_id),
    CONSTRAINT depositor_account_id FOREIGN KEY(depositor_account_id) REFERENCES account_details(account_id)
);

CREATE TABLE transaction_details(
    transaction_id INT AUTO_INCREMENT,
    from_account_id INT NOT NULL,
    to_account_id INT NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT transaction_id_pk PRIMARY KEY(transaction_id),
    CONSTRAINT from_account_id_fk FOREIGN KEY(from_account_id) REFERENCES account_details(account_id),
    CONSTRAINT to_account_id_fk FOREIGN KEY(to_account_id) REFERENCES account_details(account_id),
    CONSTRAINT transaction_type_ck CHECK(transaction_type IN('TRANSFER','CHEQUE_CLEARANCE','OVERDRAFT_CHARES','INTEREST_CREDIT'))
);

