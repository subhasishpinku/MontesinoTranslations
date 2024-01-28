package montesino.translation.montesinotranslation.model;

public class PaymentHistoryModel {
    String itemId;
    String CreatedTime;
    String TxnId;
    String QuotedAmount;
    String PaymentAmount;
    String Email;
    String QuotedAmount2;
    String DollarUsed;
    String PaymentStatus;

    public PaymentHistoryModel(String itemId, String createdTime, String txnId, String quotedAmount, String paymentAmount, String email, String quotedAmount2, String dollarUsed, String paymentStatus) {
        this.itemId = itemId;
        CreatedTime = createdTime;
        TxnId = txnId;
        QuotedAmount = quotedAmount;
        PaymentAmount = paymentAmount;
        Email = email;
        QuotedAmount2 = quotedAmount2;
        DollarUsed = dollarUsed;
        PaymentStatus = paymentStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getTxnId() {
        return TxnId;
    }

    public void setTxnId(String txnId) {
        TxnId = txnId;
    }

    public String getQuotedAmount() {
        return QuotedAmount;
    }

    public void setQuotedAmount(String quotedAmount) {
        QuotedAmount = quotedAmount;
    }

    public String getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQuotedAmount2() {
        return QuotedAmount2;
    }

    public void setQuotedAmount2(String quotedAmount2) {
        QuotedAmount2 = quotedAmount2;
    }

    public String getDollarUsed() {
        return DollarUsed;
    }

    public void setDollarUsed(String dollarUsed) {
        DollarUsed = dollarUsed;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}
