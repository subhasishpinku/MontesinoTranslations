package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardListResponse {

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("card_holder")
        @Expose
        private String cardHolder;
        @SerializedName("card_holder_nickname")
        @Expose
        private String cardHolderNickname;
        @SerializedName("card_number1")
        @Expose
        private String cardNumber1;
        @SerializedName("card_number2")
        @Expose
        private String cardNumber2;
        @SerializedName("cvv_no")
        @Expose
        private Object cvvNo;
        @SerializedName("expiry_date")
        @Expose
        private String expiryDate;
        @SerializedName("card_type")
        @Expose
        private String cardType;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("updated_by")
        @Expose
        private Object updatedBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("flag")
        @Expose
        private String flag;
        @SerializedName("card_number")
        @Expose
        private String cardNumber;
        @SerializedName("full_card_number")
        @Expose
        private String fullCardNumber;
        @SerializedName("card_icon")
        @Expose
        private String cardIcon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCardHolder() {
            return cardHolder;
        }

        public void setCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
        }

        public String getCardHolderNickname() {
            return cardHolderNickname;
        }

        public void setCardHolderNickname(String cardHolderNickname) {
            this.cardHolderNickname = cardHolderNickname;
        }

        public String getCardNumber1() {
            return cardNumber1;
        }

        public void setCardNumber1(String cardNumber1) {
            this.cardNumber1 = cardNumber1;
        }

        public String getCardNumber2() {
            return cardNumber2;
        }

        public void setCardNumber2(String cardNumber2) {
            this.cardNumber2 = cardNumber2;
        }

        public Object getCvvNo() {
            return cvvNo;
        }

        public void setCvvNo(Object cvvNo) {
            this.cvvNo = cvvNo;
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

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getFullCardNumber() {
            return fullCardNumber;
        }

        public void setFullCardNumber(String fullCardNumber) {
            this.fullCardNumber = fullCardNumber;
        }

        public String getCardIcon() {
            return cardIcon;
        }

        public void setCardIcon(String cardIcon) {
            this.cardIcon = cardIcon;
        }

    }

    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
