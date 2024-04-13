CREATE TABLE IF NOT EXISTS transactions
(
    transaction_id UUID NOT NULL PRIMARY KEY,
    amount DECIMAL(10, 2),
    transaction_date DATE,
    description TEXT
);

CREATE TABLE IF NOT EXISTS customer_transactions
(
    customer_transaction_id UUID NOT NULL PRIMARY KEY,
    customer_id UUID NOT NULL REFERENCES customer (customer_id),
    transaction_id UUID NOT NULL REFERENCES transactions (transaction_id),
    UNIQUE (customer_id, transaction_id)
);

