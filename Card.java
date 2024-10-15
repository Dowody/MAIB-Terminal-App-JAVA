public class Card {

    private String cardHolderName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCVV;
    private String cardType;
    private String cardCurrency;
    private double cardBalance;

    public Card (String cardHolderName,
                 String cardNumber,
                 String cardExpiryDate,
                 String cardCVV,
                 String cardType,
                 String cardCurrency,
                 double cardBalance)
    {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCVV = cardCVV;
        this.cardType = cardType;
        this.cardCurrency = cardCurrency;
        this.cardBalance = cardBalance;
    }

    public String getCardHolderName() { return cardHolderName; }

    public String getCardNumber() { return cardNumber; }

    public String getCardExpiryDate() { return cardExpiryDate; }

    public String getCardCVV() { return cardCVV; }

    public String getCardType() { return cardType; }

    public String getCardCurrency() { return cardCurrency; }

    public double getCardBalance() { return cardBalance; }

    public void setCardBalance(double newAmount) {
        this.cardBalance = newAmount;
    }


}
