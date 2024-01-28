package montesino.translation.montesinotranslation.model;

public class CreditDebitCardsModel {
    String cardId;
    String cardHolderName;
    String cardHolderNickname;
    String expiryDate;
    String cardType;
    String enCardNumber;
    String fullCardNumber;
    String userId;
    String accountNo;
    private boolean isChecked;
    public CreditDebitCardsModel(String cardId, String cardHolderName, String cardHolderNickname, String expiryDate, String cardType, String enCardNumber, String fullCardNumber, String userId, String accountNo) {
        this.cardId = cardId;
        this.cardHolderName = cardHolderName;
        this.cardHolderNickname = cardHolderNickname;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.enCardNumber = enCardNumber;
        this.fullCardNumber = fullCardNumber;
        this.userId = userId;
        this.accountNo = accountNo;
        this.isChecked = false;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderNickname() {
        return cardHolderNickname;
    }

    public void setCardHolderNickname(String cardHolderNickname) {
        this.cardHolderNickname = cardHolderNickname;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getEnCardNumber() {
        return enCardNumber;
    }

    public void setEnCardNumber(String enCardNumber) {
        this.enCardNumber = enCardNumber;
    }

    public String getFullCardNumber() {
        return fullCardNumber;
    }

    public void setFullCardNumber(String fullCardNumber) {
        this.fullCardNumber = fullCardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
