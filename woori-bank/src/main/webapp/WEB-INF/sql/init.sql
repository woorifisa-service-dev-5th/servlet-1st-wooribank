DROP TABLE IF EXISTS repayment; CREATE TABLE repayment (repay_id BIGINT NOT NULL AUTO_INCREMENT, repay_amount BIGINT NULL, repay_date DATETIME NULL, loan_id VARCHAR(255) NOT NULL, cl_number CHAR(13) NULL, PRIMARY KEY (repay_id));

DROP TABLE IF EXISTS guarantee; CREATE TABLE guarantee (guarantee_id BIGINT NOT NULL AUTO_INCREMENT, guarantee_name VARCHAR(255) NULL, guarantee_type ENUM('TYPE1','TYPE2') NULL, guarantee_value BIGINT NULL, guarantee_file BLOB NULL, loan_id VARCHAR(255) NOT NULL, cl_number CHAR(13) NULL, PRIMARY KEY (guarantee_id));

DROP TABLE IF EXISTS item; CREATE TABLE item (item_id BIGINT NOT NULL AUTO_INCREMENT, bank_id BIGINT NOT NULL, item_name VARCHAR(255) NULL, interest_rate DOUBLE NULL, PRIMARY KEY (item_id));

DROP TABLE IF EXISTS bank; CREATE TABLE bank (bank_id BIGINT NOT NULL AUTO_INCREMENT, balance BIGINT NULL, PRIMARY KEY (bank_id));

DROP TABLE IF EXISTS transaction; CREATE TABLE transaction (t_id INT NOT NULL AUTO_INCREMENT, account_id VARCHAR(256) NOT NULL, t_type ENUM('DEPOSIT','WITHDRAW') NULL, amount BIGINT NULL, t_date DATE NULL, description VARCHAR(256) NULL, PRIMARY KEY (t_id));

DROP TABLE IF EXISTS deposit; CREATE TABLE deposit (deposit_key VARCHAR(255) NOT NULL, item_id BIGINT NOT NULL, account_id VARCHAR(256) NOT NULL, amount BIGINT NULL, maturityDate DATETIME NULL, valid BOOLEAN NULL, PRIMARY KEY (deposit_key));

DROP TABLE IF EXISTS client; CREATE TABLE client (cl_id INT NOT NULL AUTO_INCREMENT, cl_userId VARCHAR(16) NULL, cl_userPw VARCHAR(256) NULL, cl_resi_num VARCHAR(255) NOT NULL, cl_name VARCHAR(255) NULL, cl_number CHAR(13) NULL, cl_birthday DATE NULL, cl_address VARCHAR(255) NULL, cl_email VARCHAR(255) NULL, cl_job VARCHAR(255) NULL, PRIMARY KEY (cl_id));

DROP TABLE IF EXISTS card; CREATE TABLE card (card_num VARCHAR(255) NOT NULL, card_cl_name VARCHAR(255) NULL, card_valid_date DATE NULL, card_cvc INT NULL, card_limit BIGINT NULL, account_id VARCHAR(256) NOT NULL, item_id BIGINT NOT NULL, PRIMARY KEY (card_num));

DROP TABLE IF EXISTS loan; CREATE TABLE loan (loan_id VARCHAR(255) NOT NULL, loan_amount BIGINT NULL, loan_status ENUM('ONGOING','PAID','DEFAULT') NULL, loan_date DATETIME NULL, guarantee_valid BOOLEAN NULL, account_id VARCHAR(255) NOT NULL, item_id BIGINT NOT NULL, cl_number CHAR(13) NULL, PRIMARY KEY (loan_id));

DROP TABLE IF EXISTS account; CREATE TABLE account (account_id BIGINT NOT NULL AUTO_INCREMENT, account_number VARCHAR(30) NULL, account_type ENUM('SAVING','CHECKING','LOAN') NULL, date DATE NULL, balance BIGINT NULL, cl_id INT NOT NULL, cl_job VARCHAR(255) NULL, PRIMARY KEY (account_id));