package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserManagementList {

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("is_company_admin")
        @Expose
        private String isCompanyAdmin;
        @SerializedName("is_company_admin_me")
        @Expose
        private String isCompanyAdminMe;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsCompanyAdmin() {
            return isCompanyAdmin;
        }

        public void setIsCompanyAdmin(String isCompanyAdmin) {
            this.isCompanyAdmin = isCompanyAdmin;
        }

        public String getIsCompanyAdminMe() {
            return isCompanyAdminMe;
        }

        public void setIsCompanyAdminMe(String isCompanyAdminMe) {
            this.isCompanyAdminMe = isCompanyAdminMe;
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
