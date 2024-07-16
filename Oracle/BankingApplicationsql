ALTER SESSION SET "_ORACLE_SCRIPT"=true;
CREATE USER i_nb IDENTIFIED BY i_nb;
GRANT CONNECT,DBA TO i_nb;

-- login_details
DROP TABLE login_details;
CREATE TABLE login_details(
    login_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    password VARCHAR2(50) NOT NULL,
    login_attempts NUMBER(1) NOT NULL,
    login_status VARCHAR2(10) NOT NULL,
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
    customer_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    login_id NUMBER(10),
    customer_status VARCHAR2(10),
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
    account_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    customer_id NUMBER(10),
    account_type VARCHAR(50) NOT NULL,
    balance NUMBER(10,2) NOT NULL,
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
    savings_account_id NUMBER(10),
    rate_of_interest NUMBER(5,2) DEFAULT 0.05 NOT NULL,
    minimum_balance NUMBER(10) DEFAULT 1500 NOT NULL,
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
    current_account_id NUMBER(10),
    overdraft_balance NUMBER(10) NOT NULL,
    remaining_overdraft_balance NUMBER(10) NOT NULL,
    minimum_balance NUMBER(10) DEFAULT 0 NOT NULL,
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
    fixed_deposit_interest_validity_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    validity_in_months NUMBER(2) NOT NULL, 
    rate_of_interest NUMBER(4,3) NOT NULL,
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
    fixed_deposit_id NUMBER(10),
    amount NUMBER(10) NOT NULL,
    maturity_amount NUMBER(10) NOT NULL,
    fixed_deposit_validity_interest_id NUMBER(10),
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
    cheque_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    receiver_name VARCHAR(50),
    cheque_date DATE,
    amount NUMBER(10,2),
    account_id NUMBER(10) NOT NULL,
    cheque_status VARCHAR(50) NOT NULL,
    CONSTRAINT cheque_status_ck CHECK(cheque_status IN ('NEW','ISSUED','DEPOSITED','CLEARED','BOUNCED')),
    CONSTRAINT cheque_amount_ck CHECK(amount > 0),
    CONSTRAINT cheque_id_pk PRIMARY KEY(cheque_id),
    CONSTRAINT account_id_fk FOREIGN KEY(account_id) REFERENCES account_details(account_id)
);

-- bank_slip_details
CREATE TABLE bank_slip_details(
    slip_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    cheque_id NUMBER(10) NOT NULL,
    depositor_account_id NUMBER(10) NOT NULL,
    CONSTRAINT slip_id_pk PRIMARY KEY(slip_id),
    CONSTRAINT cheque_id_fk FOREIGN KEY(cheque_id) REFERENCES cheque_details(cheque_id),
    CONSTRAINT depositor_account_id FOREIGN KEY(depositor_account_id) REFERENCES account_details(account_id)
);

CREATE TABLE transaction_details(
    transaction_id NUMBER(10) GENERATED ALWAYS AS IDENTITY,
    from_account_id NUMBER(10) NOT NULL,
    to_account_id NUMBER(10) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_date_time DATE DEFAULT SYSDATE,
    CONSTRAINT transaction_id_pk PRIMARY KEY(transaction_id),
    CONSTRAINT from_account_id_fk FOREIGN KEY(from_account_id) REFERENCES account_details(account_id),
    CONSTRAINT to_account_id_fk FOREIGN KEY(to_account_id) REFERENCES account_details(account_id),
    CONSTRAINT transaction_type_ck CHECK(transaction_type IN('TRANSFER','CHEQUE_CLEARANCE','OVERDRAFT_CHARES','INTEREST_CREDIT'))
);

-- Generate Cheques 
SELECT * FROM account_details;
SELECT * FROM cheque_details;
DESC cheque_details;

CREATE PROCEDURE sp_generate_cheques(account_id NUMBER)
AS
BEGIN
  FOR l_counter IN 1..10
  LOOP
    INSERT INTO cheque_details(account_id,cheque_status) VALUES(account_id,'NEW');
  END LOOP;
END;
/

CREATE OR REPLACE TRIGGER tg_insert_account_details
AFTER 
INSERT 
ON account_details
FOR EACH ROW
BEGIN
    sp_generate_cheques(:NEW.account_id);
END;
/

DESC account_details;
INSERT INTO account_details(customer_id,account_type,balance)
VALUES(1,'CURRENT',10000);

SELECT * FROM account_details;
SELECT * FROM cheque_details;


-- login validation
SELECT * FROM login_details;
SELECT * FROM customer_details;

SELECT * FROM login_details WHERE login_id = (
SELECT login_id FROM customer_details WHERE customer_id = 1)
AND password = 'Bahubali@1234';



CREATE OR REPLACE PROCEDURE sp_login_validation(p_customer_id IN NUMBER,p_password IN VARCHAR2,flag IN OUT NUMBER)
AS
    r_login_details login_details%ROWTYPE;
    login_id_row_count NUMBER(1);
BEGIN
    SELECT COUNT(login_id) INTO login_id_row_count FROM login_details WHERE login_id = (
        SELECT login_id FROM customer_details WHERE customer_id = p_customer_id);
    
    IF login_id_row_count = 1 THEN
         DBMS_OUTPUT.PUT_LINE('Valid customer_id , now cheking password');
         
         SELECT COUNT(login_id) INTO login_id_row_count FROM login_details WHERE login_id = (
            SELECT login_id FROM customer_details WHERE customer_id = p_customer_id)
            AND password = p_password;
    
        IF login_id_row_count = 1 THEN 
            DBMS_OUTPUT.PUT_LINE('login successfull');
            flag := 1;
        ELSE
            DBMS_OUTPUT.PUT_LINE('incorrect password return 0');
            flag := 0;
            
            SELECT * INTO r_login_details FROM login_details WHERE login_id = (
                SELECT login_id FROM customer_details WHERE customer_id = p_customer_id);
            
            IF r_login_details.login_attempts < 3 AND r_login_details.login_status = 'ACTIVE' THEN
                UPDATE login_details SET login_attempts = login_attempts + 1 WHERE login_id = r_login_details.login_id;
                COMMIT;
            END IF;
            
             SELECT * INTO r_login_details FROM login_details WHERE login_id = (
                SELECT login_id FROM customer_details WHERE customer_id = p_customer_id);
                
            IF r_login_details.login_attempts = 3 THEN
                UPDATE login_details SET login_status = 'LOCKED' WHERE login_id = r_login_details.login_id;
                COMMIT;
                DBMS_OUTPUT.PUT_LINE('blocked');
            END IF;
                        
        END IF;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Invalid customer_id return -1');
        flag := -1;
    END IF;
    
END;
/

SET SERVEROUTPUT ON;

DECLARE
    customer_id NUMBER(10) := 1;
    password VARCHAR(20) := 'Bahubali@123';
    flag NUMBER(1) := -1;
BEGIN
    sp_login_validation(customer_id,password,flag);
    DBMS_OUTPUT.PUT_LINE(flag);
END;
/

SELECT * FROM login_details;


