CREATE TABLE Investment (
    id INT IDENTITY(1,1) PRIMARY KEY,
    total_investment DECIMAL(18,2) NOT NULL,
    gold_gram DECIMAL(18,3) NOT NULL,
    gold_worth DECIMAL(18,2) NOT NULL,
    gold_worth_currency NVARCHAR(10) NOT NULL
);
