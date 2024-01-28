package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentHistoryResponse {
    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("txnid")
        @Expose
        private String txnid;
        @SerializedName("quoted_amount")
        @Expose
        private String quotedAmount;
        @SerializedName("payment_amount")
        @Expose
        private String paymentAmount;
        @SerializedName("t_dollar_used")
        @Expose
        private String tDollarUsed;
        @SerializedName("coupon_id")
        @Expose
        private String couponId;
        @SerializedName("coupon_amount_used")
        @Expose
        private String couponAmountUsed;
        @SerializedName("payment_status")
        @Expose
        private String paymentStatus;
        @SerializedName("createdtime")
        @Expose
        private String createdtime;
        @SerializedName("itemid")
        @Expose
        private String itemid;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("flag")
        @Expose
        private String flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTxnid() {
            return txnid;
        }

        public void setTxnid(String txnid) {
            this.txnid = txnid;
        }

        public String getQuotedAmount() {
            return quotedAmount;
        }

        public void setQuotedAmount(String quotedAmount) {
            this.quotedAmount = quotedAmount;
        }

        public String getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(String paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String gettDollarUsed() {
            return tDollarUsed;
        }

        public void settDollarUsed(String tDollarUsed) {
            this.tDollarUsed = tDollarUsed;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponAmountUsed() {
            return couponAmountUsed;
        }

        public void setCouponAmountUsed(String couponAmountUsed) {
            this.couponAmountUsed = couponAmountUsed;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(String createdtime) {
            this.createdtime = createdtime;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
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
