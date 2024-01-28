package montesino.translation.montesinotranslation.model;

public class CardModel {
    String cardId;
    String cardHolderName;
    String cardHolderNickname;
    String expiryDate;
    String cardType;
    String enCardNumber;
    String fullCardNumber;
    String userId;
    String accountNo;

    public CardModel(String cardId, String cardHolderName, String cardHolderNickname, String expiryDate, String cardType, String enCardNumber, String fullCardNumber,  String userId, String accountNo) {
        this.cardId = cardId;
        this.cardHolderName = cardHolderName;
        this.cardHolderNickname = cardHolderNickname;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.enCardNumber = enCardNumber;
        this.fullCardNumber = fullCardNumber;
        this.userId = userId;
        this.accountNo = accountNo;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardHolderNickname() {
        return cardHolderNickname;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public String getEnCardNumber() {
        return enCardNumber;
    }

    public String getFullCardNumber() {
        return fullCardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountNo() {
        return accountNo;
    }
}
