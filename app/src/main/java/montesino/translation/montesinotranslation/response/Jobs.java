package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jobs {

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("job_name")
        @Expose
        private String jobName;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("invoice_number")
        @Expose
        private String invoiceNumber;
        @SerializedName("quoted_amount")
        @Expose
        private String quotedAmount;
        @SerializedName("paid_amount")
        @Expose
        private String paidAmount;
        @SerializedName("actual_amount")
        @Expose
        private String actualAmount;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("payment")
        @Expose
        private String payment;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("expedite_charge")
        @Expose
        private String expediteCharge;
        @SerializedName("t_dollar_to_be_added")
        @Expose
        private String tDollarToBeAdded;
        @SerializedName("t_dollar_used_actual")
        @Expose
        private String tDollarUsedActual;
        @SerializedName("t_dollar_used")
        @Expose
        private String tDollarUsed;
        @SerializedName("coupon_id")
        @Expose
        private String couponId;
        @SerializedName("coupon_amount_used")
        @Expose
        private String couponAmountUsed;
        @SerializedName("notorized_copy_added")
        @Expose
        private String notorizedCopyAdded;
        @SerializedName("notorized_copies")
        @Expose
        private String notorizedCopies;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("tx_line_no")
        @Expose
        private String txLineNo;
        @SerializedName("quote_by")
        @Expose
        private String quoteBy;
        @SerializedName("admin_notified")
        @Expose
        private String adminNotified;
        @SerializedName("client_notified")
        @Expose
        private String clientNotified;
        @SerializedName("due_date")
        @Expose
        private String dueDate;
        @SerializedName("status_change_date")
        @Expose
        private String statusChangeDate;
        @SerializedName("reminder_date")
        @Expose
        private String reminderDate;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("company_id")
        @Expose
        private String companyId;
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
        @SerializedName("file")
        @Expose
        private String file;
        @SerializedName("file_type")
        @Expose
        private String fileType;
        @SerializedName("file_size")
        @Expose
        private String fileSize;
        @SerializedName("reminder_email")
        @Expose
        private String reminderEmail;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("sales_rep")
        @Expose
        private String salesRep;
        @SerializedName("completion_time")
        @Expose
        private String completionTime;
        @SerializedName("register_date")
        @Expose
        private String registerDate;
        @SerializedName("page")
        @Expose
        private String page;
        @SerializedName("file_name")
        @Expose
        private String fileName;
        @SerializedName("file_words_or_pages")
        @Expose
        private String fileWordsOrPages;
        @SerializedName("unit_price")
        @Expose
        private String unitPrice;
        @SerializedName("page_amount")
        @Expose
        private String pageAmount;
        @SerializedName("page_or_word")
        @Expose
        private String pageOrWord;
        @SerializedName("quote_items")
        @Expose
        private String quoteItems;
        @SerializedName("quote_desc")
        @Expose
        private String quoteDesc;
        @SerializedName("translate_to")
        @Expose
        private String translateTo;
        @SerializedName("translate_from")
        @Expose
        private String translateFrom;
        @SerializedName("words_perfile")
        @Expose
        private String wordsPerfile;
        @SerializedName("quote_file")
        @Expose
        private String quoteFile;
        @SerializedName("ori_file_name")
        @Expose
        private String oriFileName;
        @SerializedName("is_file_deleted")
        @Expose
        private String isFileDeleted;
        @SerializedName("reminder_email_date")
        @Expose
        private String reminderEmailDate;
        @SerializedName("auto_reminder_email_sent")
        @Expose
        private String autoReminderEmailSent;
        @SerializedName("overdue_reminder_email_sent")
        @Expose
        private String overdueReminderEmailSent;
        @SerializedName("overdue_auto_mail_sent")
        @Expose
        private String overdueAutoMailSent;
        @SerializedName("overdue_auto_mail_sent_date")
        @Expose
        private String overdueAutoMailSentDate;
        @SerializedName("client_confirm_sent_amount")
        @Expose
        private String clientConfirmSentAmount;
        @SerializedName("client_confirm_sent_amount_date")
        @Expose
        private String clientConfirmSentAmountDate;
        @SerializedName("client_confirm_sent_amount_to_admin")
        @Expose
        private String clientConfirmSentAmountToAdmin;
        @SerializedName("client_confirm_sent_amount_to_admin_date")
        @Expose
        private String clientConfirmSentAmountToAdminDate;
        @SerializedName("total_amount_last")
        @Expose
        private String totalAmountLast;
        @SerializedName("action_status")
        @Expose
        private String actionStatus;
        @SerializedName("is_client_submitted")
        @Expose
        private String isClientSubmitted;
        @SerializedName("is_admin_status_change")
        @Expose
        private String isAdminStatusChange;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("modified_at")
        @Expose
        private String modifiedAt;
        @SerializedName("modified_by")
        @Expose
        private String modifiedBy;
        @SerializedName("modified_user_type")
        @Expose
        private String modifiedUserType;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("created_user_type")
        @Expose
        private String createdUserType;
        @SerializedName("flag")
        @Expose
        private String flag;
        @SerializedName("ip_address")
        @Expose
        private String ipAddress;
        @SerializedName("pdf_link")
        @Expose
        private String pdfLink;
        @SerializedName("pdf_permission")
        @Expose
        private String pdfPermission;
        @SerializedName("job_status")
        @Expose
        private String jobStatus;
        @SerializedName("show_status")
        @Expose
        private String showStatus;
        @SerializedName("first_copy_price")
        @Expose
        private String firstCopyPrice;
        @SerializedName("additional_copy_price")
        @Expose
        private String additionalCopyPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
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

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public String getQuotedAmount() {
            return quotedAmount;
        }

        public void setQuotedAmount(String quotedAmount) {
            this.quotedAmount = quotedAmount;
        }

        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(String actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getExpediteCharge() {
            return expediteCharge;
        }

        public void setExpediteCharge(String expediteCharge) {
            this.expediteCharge = expediteCharge;
        }

        public String gettDollarToBeAdded() {
            return tDollarToBeAdded;
        }

        public void settDollarToBeAdded(String tDollarToBeAdded) {
            this.tDollarToBeAdded = tDollarToBeAdded;
        }

        public String gettDollarUsedActual() {
            return tDollarUsedActual;
        }

        public void settDollarUsedActual(String tDollarUsedActual) {
            this.tDollarUsedActual = tDollarUsedActual;
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

        public String getNotorizedCopyAdded() {
            return notorizedCopyAdded;
        }

        public void setNotorizedCopyAdded(String notorizedCopyAdded) {
            this.notorizedCopyAdded = notorizedCopyAdded;
        }

        public String getNotorizedCopies() {
            return notorizedCopies;
        }

        public void setNotorizedCopies(String notorizedCopies) {
            this.notorizedCopies = notorizedCopies;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getTxLineNo() {
            return txLineNo;
        }

        public void setTxLineNo(String txLineNo) {
            this.txLineNo = txLineNo;
        }

        public String getQuoteBy() {
            return quoteBy;
        }

        public void setQuoteBy(String quoteBy) {
            this.quoteBy = quoteBy;
        }

        public String getAdminNotified() {
            return adminNotified;
        }

        public void setAdminNotified(String adminNotified) {
            this.adminNotified = adminNotified;
        }

        public String getClientNotified() {
            return clientNotified;
        }

        public void setClientNotified(String clientNotified) {
            this.clientNotified = clientNotified;
        }

        public Object getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getStatusChangeDate() {
            return statusChangeDate;
        }

        public void setStatusChangeDate(String statusChangeDate) {
            this.statusChangeDate = statusChangeDate;
        }

        public String getReminderDate() {
            return reminderDate;
        }

        public void setReminderDate(String reminderDate) {
            this.reminderDate = reminderDate;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
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

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getReminderEmail() {
            return reminderEmail;
        }

        public void setReminderEmail(String reminderEmail) {
            this.reminderEmail = reminderEmail;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getSalesRep() {
            return salesRep;
        }

        public void setSalesRep(String salesRep) {
            this.salesRep = salesRep;
        }

        public String getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(String completionTime) {
            this.completionTime = completionTime;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileWordsOrPages() {
            return fileWordsOrPages;
        }

        public void setFileWordsOrPages(String fileWordsOrPages) {
            this.fileWordsOrPages = fileWordsOrPages;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getPageAmount() {
            return pageAmount;
        }

        public void setPageAmount(String pageAmount) {
            this.pageAmount = pageAmount;
        }

        public String getPageOrWord() {
            return pageOrWord;
        }

        public void setPageOrWord(String pageOrWord) {
            this.pageOrWord = pageOrWord;
        }

        public String getQuoteItems() {
            return quoteItems;
        }

        public void setQuoteItems(String quoteItems) {
            this.quoteItems = quoteItems;
        }

        public String getQuoteDesc() {
            return quoteDesc;
        }

        public void setQuoteDesc(String quoteDesc) {
            this.quoteDesc = quoteDesc;
        }

        public String getTranslateTo() {
            return translateTo;
        }

        public void setTranslateTo(String translateTo) {
            this.translateTo = translateTo;
        }

        public String getTranslateFrom() {
            return translateFrom;
        }

        public void setTranslateFrom(String translateFrom) {
            this.translateFrom = translateFrom;
        }

        public String getWordsPerfile() {
            return wordsPerfile;
        }

        public void setWordsPerfile(String wordsPerfile) {
            this.wordsPerfile = wordsPerfile;
        }

        public String getQuoteFile() {
            return quoteFile;
        }

        public void setQuoteFile(String quoteFile) {
            this.quoteFile = quoteFile;
        }

        public String getOriFileName() {
            return oriFileName;
        }

        public void setOriFileName(String oriFileName) {
            this.oriFileName = oriFileName;
        }

        public String getIsFileDeleted() {
            return isFileDeleted;
        }

        public void setIsFileDeleted(String isFileDeleted) {
            this.isFileDeleted = isFileDeleted;
        }

        public String getReminderEmailDate() {
            return reminderEmailDate;
        }

        public void setReminderEmailDate(String reminderEmailDate) {
            this.reminderEmailDate = reminderEmailDate;
        }

        public String getAutoReminderEmailSent() {
            return autoReminderEmailSent;
        }

        public void setAutoReminderEmailSent(String autoReminderEmailSent) {
            this.autoReminderEmailSent = autoReminderEmailSent;
        }

        public String getOverdueReminderEmailSent() {
            return overdueReminderEmailSent;
        }

        public void setOverdueReminderEmailSent(String overdueReminderEmailSent) {
            this.overdueReminderEmailSent = overdueReminderEmailSent;
        }

        public String getOverdueAutoMailSent() {
            return overdueAutoMailSent;
        }

        public void setOverdueAutoMailSent(String overdueAutoMailSent) {
            this.overdueAutoMailSent = overdueAutoMailSent;
        }

        public String getOverdueAutoMailSentDate() {
            return overdueAutoMailSentDate;
        }

        public void setOverdueAutoMailSentDate(String overdueAutoMailSentDate) {
            this.overdueAutoMailSentDate = overdueAutoMailSentDate;
        }

        public String getClientConfirmSentAmount() {
            return clientConfirmSentAmount;
        }

        public void setClientConfirmSentAmount(String clientConfirmSentAmount) {
            this.clientConfirmSentAmount = clientConfirmSentAmount;
        }

        public String getClientConfirmSentAmountDate() {
            return clientConfirmSentAmountDate;
        }

        public void setClientConfirmSentAmountDate(String clientConfirmSentAmountDate) {
            this.clientConfirmSentAmountDate = clientConfirmSentAmountDate;
        }

        public String getClientConfirmSentAmountToAdmin() {
            return clientConfirmSentAmountToAdmin;
        }

        public void setClientConfirmSentAmountToAdmin(String clientConfirmSentAmountToAdmin) {
            this.clientConfirmSentAmountToAdmin = clientConfirmSentAmountToAdmin;
        }

        public String getClientConfirmSentAmountToAdminDate() {
            return clientConfirmSentAmountToAdminDate;
        }

        public void setClientConfirmSentAmountToAdminDate(String clientConfirmSentAmountToAdminDate) {
            this.clientConfirmSentAmountToAdminDate = clientConfirmSentAmountToAdminDate;
        }

        public String getTotalAmountLast() {
            return totalAmountLast;
        }

        public void setTotalAmountLast(String totalAmountLast) {
            this.totalAmountLast = totalAmountLast;
        }

        public String getActionStatus() {
            return actionStatus;
        }

        public void setActionStatus(String actionStatus) {
            this.actionStatus = actionStatus;
        }

        public String getIsClientSubmitted() {
            return isClientSubmitted;
        }

        public void setIsClientSubmitted(String isClientSubmitted) {
            this.isClientSubmitted = isClientSubmitted;
        }

        public String getIsAdminStatusChange() {
            return isAdminStatusChange;
        }

        public void setIsAdminStatusChange(String isAdminStatusChange) {
            this.isAdminStatusChange = isAdminStatusChange;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getPdfLink() {
            return pdfLink;
        }

        public void setPdfLink(String pdfLink) {
            this.pdfLink = pdfLink;
        }

        public String getPdfPermission() {
            return pdfPermission;
        }

        public void setPdfPermission(String pdfPermission) {
            this.pdfPermission = pdfPermission;
        }

        public String getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(String jobStatus) {
            this.jobStatus = jobStatus;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getFirstCopyPrice() {
            return firstCopyPrice;
        }

        public void setFirstCopyPrice(String firstCopyPrice) {
            this.firstCopyPrice = firstCopyPrice;
        }

        public String getAdditionalCopyPrice() {
            return additionalCopyPrice;
        }

        public void setAdditionalCopyPrice(String additionalCopyPrice) {
            this.additionalCopyPrice = additionalCopyPrice;
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
