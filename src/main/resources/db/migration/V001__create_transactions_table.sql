CREATE TABLE transactions(
    id INT(255) NOT NULL AUTO_INCREMENT,
    account_id INT(255) NOT NULL,
    amount FLOAT(15) NOT NULL,
    merchant VARCHAR(100) NOT NULL,
    mcc INT(5) NOT NULL,
    PRIMARY KEY (id)
);
