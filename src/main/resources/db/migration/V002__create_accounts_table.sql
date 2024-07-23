CREATE TABLE accounts(
    id INT(255) NOT NULL AUTO_INCREMENT,
    balance_food DECIMAL(50) NOT NULL,
    balance_meal DECIMAL(50) NOT NULL,
    balance_cash DECIMAL(50) NOT NULL,
    PRIMARY KEY (id)
);