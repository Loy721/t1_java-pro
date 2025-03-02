CREATE TABLE products
(
    id             bigserial PRIMARY KEY,
    account_number bigint   NOT NULL,
    balance        bigint   NOT NULL,
    product_type   VARCHAR(32) NOT NULL,
    user_id        bigint   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO products (account_number, balance, product_type, user_id)
VALUES (123456789, 100, 'ACCOUNT', 1);
INSERT INTO products (account_number, balance, product_type, user_id)
VALUES (123456780, 200, 'CARD', 1);
INSERT INTO products (account_number, balance, product_type, user_id)
VALUES (123456781, 300, 'ACCOUNT', 2);
INSERT INTO products (account_number, balance, product_type, user_id)
VALUES (123456782, 400, 'ACCOUNT', 3);
INSERT INTO products (account_number, balance, product_type, user_id)
VALUES (123456783, 500, 'CARD', 4);