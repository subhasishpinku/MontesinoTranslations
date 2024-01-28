package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalInfoResponse {

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
        @SerializedName("reward_credit")
        @Expose
        private String rewardCredit;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("is_changed_password")
        @Expose
        private String isChangedPassword;
        @SerializedName("is_company_admin")
        @Expose
        private String isCompanyAdmin;
        @SerializedName("is_company_admin_me")
        @Expose
        private String isCompanyAdminMe;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("invoicingOption")
        @Expose
        private String invoicingOption;
        @SerializedName("address1")
        @Expose
        private String address1;
        @SerializedName("address2")
        @Expose
        private String address2;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("company_id")
        @Expose
        private String companyId;
        @SerializedName("company_activated")
        @Expose
        private String companyActivated;
        @SerializedName("individual_or_company")
        @Expose
        private String individualOrCompany;
        @SerializedName("address1_shipping")
        @Expose
        private String address1Shipping;
        @SerializedName("address2_shipping")
        @Expose
        private String address2Shipping;
        @SerializedName("city_shipping")
        @Expose
        private String cityShipping;
        @SerializedName("state_shipping")
        @Expose
        private String stateShipping;
        @SerializedName("country_shipping")
        @Expose
        private String countryShipping;
        @SerializedName("zipcode_shipping")
        @Expose
        private String zipcodeShipping;
        @SerializedName("address1_shipping2")
        @Expose
        private String address1Shipping2;
        @SerializedName("address2_shipping2")
        @Expose
        private String address2Shipping2;
        @SerializedName("city_shipping2")
        @Expose
        private String cityShipping2;
        @SerializedName("state_shipping2")
        @Expose
        private String stateShipping2;
        @SerializedName("country_shipping2")
        @Expose
        private String countryShipping2;
        @SerializedName("zipcode_shipping2")
        @Expose
        private String zipcodeShipping2;
        @SerializedName("change_email")
        @Expose
        private String changeEmail;
        @SerializedName("uniq_key")
        @Expose
        private String uniqKey;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("deleted")
        @Expose
        private String deleted;
        @SerializedName("reg_date")
        @Expose
        private String regDate;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("created_user_type")
        @Expose
        private String createdUserType;
        @SerializedName("modified_at")
        @Expose
        private String modifiedAt;
        @SerializedName("modified_by")
        @Expose
        private String modifiedBy;
        @SerializedName("modified_user_type")
        @Expose
        private String modifiedUserType;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("translate_from")
        @Expose
        private String translateFrom;
        @SerializedName("translate_to")
        @Expose
        private String translateTo;
        @SerializedName("flag")
        @Expose
        private String flag;

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

        public String getRewardCredit() {
            return rewardCredit;
        }

        public void setRewardCredit(String rewardCredit) {
            this.rewardCredit = rewardCredit;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIsChangedPassword() {
            return isChangedPassword;
        }

        public void setIsChangedPassword(String isChangedPassword) {
            this.isChangedPassword = isChangedPassword;
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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getInvoicingOption() {
            return invoicingOption;
        }

        public void setInvoicingOption(String invoicingOption) {
            this.invoicingOption = invoicingOption;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyActivated() {
            return companyActivated;
        }

        public void setCompanyActivated(String companyActivated) {
            this.companyActivated = companyActivated;
        }

        public String getIndividualOrCompany() {
            return individualOrCompany;
        }

        public void setIndividualOrCompany(String individualOrCompany) {
            this.individualOrCompany = individualOrCompany;
        }

        public String getAddress1Shipping() {
            return address1Shipping;
        }

        public void setAddress1Shipping(String address1Shipping) {
            this.address1Shipping = address1Shipping;
        }

        public String getAddress2Shipping() {
            return address2Shipping;
        }

        public void setAddress2Shipping(String address2Shipping) {
            this.address2Shipping = address2Shipping;
        }

        public String getCityShipping() {
            return cityShipping;
        }

        public void setCityShipping(String cityShipping) {
            this.cityShipping = cityShipping;
        }

        public String getStateShipping() {
            return stateShipping;
        }

        public void setStateShipping(String stateShipping) {
            this.stateShipping = stateShipping;
        }

        public String getCountryShipping() {
            return countryShipping;
        }

        public void setCountryShipping(String countryShipping) {
            this.countryShipping = countryShipping;
        }

        public String getZipcodeShipping() {
            return zipcodeShipping;
        }

        public void setZipcodeShipping(String zipcodeShipping) {
            this.zipcodeShipping = zipcodeShipping;
        }

        public String getAddress1Shipping2() {
            return address1Shipping2;
        }

        public void setAddress1Shipping2(String address1Shipping2) {
            this.address1Shipping2 = address1Shipping2;
        }

        public String getAddress2Shipping2() {
            return address2Shipping2;
        }

        public void setAddress2Shipping2(String address2Shipping2) {
            this.address2Shipping2 = address2Shipping2;
        }

        public String getCityShipping2() {
            return cityShipping2;
        }

        public void setCityShipping2(String cityShipping2) {
            this.cityShipping2 = cityShipping2;
        }

        public String getStateShipping2() {
            return stateShipping2;
        }

        public void setStateShipping2(String stateShipping2) {
            this.stateShipping2 = stateShipping2;
        }

        public String getCountryShipping2() {
            return countryShipping2;
        }

        public void setCountryShipping2(String countryShipping2) {
            this.countryShipping2 = countryShipping2;
        }

        public String getZipcodeShipping2() {
            return zipcodeShipping2;
        }

        public void setZipcodeShipping2(String zipcodeShipping2) {
            this.zipcodeShipping2 = zipcodeShipping2;
        }

        public String getChangeEmail() {
            return changeEmail;
        }

        public void setChangeEmail(String changeEmail) {
            this.changeEmail = changeEmail;
        }

        public String getUniqKey() {
            return uniqKey;
        }

        public void setUniqKey(String uniqKey) {
            this.uniqKey = uniqKey;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedUserType() {
            return createdUserType;
        }

        public void setCreatedUserType(String createdUserType) {
            this.createdUserType = createdUserType;
        }

        public String getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModifiedUserType() {
            return modifiedUserType;
        }

        public void setModifiedUserType(String modifiedUserType) {
            this.modifiedUserType = modifiedUserType;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getTranslateFrom() {
            return translateFrom;
        }

        public void setTranslateFrom(String translateFrom) {
            this.translateFrom = translateFrom;
        }

        public String getTranslateTo() {
            return translateTo;
        }

        public void setTranslateTo(String translateTo) {
            this.translateTo = translateTo;
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
