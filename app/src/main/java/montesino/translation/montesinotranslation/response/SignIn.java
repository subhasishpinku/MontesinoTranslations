package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignIn {

    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("invoicingOption")
        @Expose
        private String invoicingOption;
        @SerializedName("is_admin")
        @Expose
        private String isAdmin;
        @SerializedName("is_default_admin")
        @Expose
        private String isDefaultAdmin;
        @SerializedName("reward_credit")
        @Expose
        private String rewardCredit;
        @SerializedName("is_changed_password")
        @Expose
        private String isChangedPassword;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getInvoicingOption() {
            return invoicingOption;
        }

        public void setInvoicingOption(String invoicingOption) {
            this.invoicingOption = invoicingOption;
        }

        public String getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(String isAdmin) {
            this.isAdmin = isAdmin;
        }

        public String getIsDefaultAdmin() {
            return isDefaultAdmin;
        }

        public void setIsDefaultAdmin(String isDefaultAdmin) {
            this.isDefaultAdmin = isDefaultAdmin;
        }

        public String getRewardCredit() {
            return rewardCredit;
        }

        public void setRewardCredit(String rewardCredit) {
            this.rewardCredit = rewardCredit;
        }

        public String getIsChangedPassword() {
            return isChangedPassword;
        }

        public void setIsChangedPassword(String isChangedPassword) {
            this.isChangedPassword = isChangedPassword;
        }

    }

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
