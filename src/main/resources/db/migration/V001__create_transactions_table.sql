CREATE TABLE transactions(
    id INT(30) NOT NULL AUTO_INCREMENT,
    account_id INT(30) NOT NULL,
    amount FLOAT(15) NOT NULL,
    merchant VARCHAR(100) NOT NULL,
    mcc VARCHAR(5) NOT NULL,
    PRIMARY KEY (id)
);
