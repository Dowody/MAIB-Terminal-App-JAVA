import java.time.LocalDateTime;

public class Transaction {

    private final String accountID;
    private final String transactionType;
    private final String transactionTitle;
    private final LocalDateTime transactionTime;
    private final double transactionAmount;
    private final String transactionCurrency;


    public Transaction(String accountID, String transactionType, String transactionTitle, LocalDateTime transactionTime, double transactionAmount, String transactionCurrency)
    {
        this.accountID = accountID;
        this.transactionType = transactionType;
        this.transactionTitle = transactionTitle;
        this.transactionTime = transactionTime;
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
    }

    public String getAccountID() { return accountID; }

    public String getTransactionType() { return transactionType; }

    public String getTransactionTitle() { return transactionTitle; }

    public LocalDateTime getTransactionTime() { return transactionTime; }

    public double getTransactionAmount() { return transactionAmount; }

    public String getTransactionCurrency() { return transactionCurrency; }

}
