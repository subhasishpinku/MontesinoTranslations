package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobListDetailsResponse {
    public class Example {

        @SerializedName("ProjectName")
        @Expose
        private String projectName;
        @SerializedName("QuotedId")
        @Expose
        private String quotedId;
        @SerializedName("TranslateFrom")
        @Expose
        private String translateFrom;
        @SerializedName("TranslateTo")
        @Expose
        private String translateTo;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("assignTo")
        @Expose
        private String assignTo;
        @SerializedName("companyName")
        @Expose
        private String companyName;
        @SerializedName("invNumber")
        @Expose
        private String invNumber;
        @SerializedName("jsonObject")
        @Expose
        private JsonObject jsonObject;
        @SerializedName("padeType")
        @Expose
        private String padeType;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getQuotedId() {
            return quotedId;
        }

        public void setQuotedId(String quotedId) {
            this.quotedId = quotedId;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAssignTo() {
            return assignTo;
        }

        public void setAssignTo(String assignTo) {
            this.assignTo = assignTo;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getInvNumber() {
            return invNumber;
        }

        public void setInvNumber(String invNumber) {
            this.invNumber = invNumber;
        }

        public JsonObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        public String getPadeType() {
            return padeType;
        }

        public void setPadeType(String padeType) {
            this.padeType = padeType;
        }

    }
    public class JsonObject {
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("actual_amount")
        @Expose
        private String actualAmount;
        @SerializedName("additional_copy_price")
        @Expose
        private String additionalCopyPrice;
        @SerializedName("address1")
        @Expose
        private String address1;
        @SerializedName("address2")
        @Expose
        private String address2;
        @SerializedName("admin_notified")
        @Expose
        private String adminNotified;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("auto_reminder_email_sent")
        @Expose
        private String autoReminderEmailSent;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("client_confirm_sent_amount")
        @Expose
        private String clientConfirmSentAmount;
        @SerializedName("client_notified")
        @Expose
        private String clientNotified;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("completion_time")
        @Expose
        private String completionTime;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("coupon_amount_used")
        @Expose
        private String couponAmountUsed;
        @SerializedName("coupon_id")
        @Expose
        private String couponId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("expedite_charge")
        @Expose
        private String expediteCharge;
        @SerializedName("file_words_or_pages")
        @Expose
        private String fileWordsOrPages;
        @SerializedName("first_copy_price")
        @Expose
        private String firstCopyPrice;
        @SerializedName("flag")
        @Expose
        private String flag;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("invoice_number")
        @Expose
        private String invoiceNumber;
        @SerializedName("ip_address")
        @Expose
        private String ipAddress;
        @SerializedName("is_client_submitted")
        @Expose
        private String isClientSubmitted;
        @SerializedName("is_file_deleted")
        @Expose
        private String isFileDeleted;
        @SerializedName("job_name")
        @Expose
        private String jobName;
        @SerializedName("job_status")
        @Expose
        private String jobStatus;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("notorized_copies")
        @Expose
        private String notorizedCopies;
        @SerializedName("notorized_copy_added")
        @Expose
        private String notorizedCopyAdded;
        @SerializedName("overdue_auto_mail_sent")
        @Expose
        private String overdueAutoMailSent;
        @SerializedName("overdue_auto_mail_sent_date")
        @Expose
        private String overdueAutoMailSentDate;
        @SerializedName("overdue_reminder_email_sent")
        @Expose
        private String overdueReminderEmailSent;
        @SerializedName("page_amount")
        @Expose
        private String pageAmount;
        @SerializedName("page_or_word")
        @Expose
        private String pageOrWord;
        @SerializedName("paid_amount")
        @Expose
        private String paidAmount;
        @SerializedName("payment")
        @Expose
        private String payment;
        @SerializedName("pdf_link")
        @Expose
        private String pdfLink;
        @SerializedName("pdf_permission")
        @Expose
        private String pdfPermission;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("quote_by")
        @Expose
        private String quoteBy;
        @SerializedName("quoted_amount")
        @Expose
        private String quotedAmount;
        @SerializedName("register_date")
        @Expose
        private String registerDate;
        @SerializedName("reminder_email")
        @Expose
        private String reminderEmail;
        @SerializedName("show_status")
        @Expose
        private String showStatus;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("t_dollar_to_be_added")
        @Expose
        private String tDollarToBeAdded;
        @SerializedName("t_dollar_used")
        @Expose
        private String tDollarUsed;
        @SerializedName("t_dollar_used_actual")
        @Expose
        private String tDollarUsedActual;
        @SerializedName("total_amount_last")
        @Expose
        private String totalAmountLast;
        @SerializedName("translate_from")
        @Expose
        private String translateFrom;
        @SerializedName("translate_to")
        @Expose
        private String translateTo;
        @SerializedName("tx_line_no")
        @Expose
        private String txLineNo;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(String actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getAdditionalCopyPrice() {
            return additionalCopyPrice;
        }

        public void setAdditionalCopyPrice(String additionalCopyPrice) {
            this.additionalCopyPrice = additionalCopyPrice;
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

        public String getAdminNotified() {
            return adminNotified;
        }

        public void setAdminNotified(String adminNotified) {
            this.adminNotified = adminNotified;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAutoReminderEmailSent() {
            return autoReminderEmailSent;
        }

        public void setAutoReminderEmailSent(String autoReminderEmailSent) {
            this.autoReminderEmailSent = autoReminderEmailSent;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getClientConfirmSentAmount() {
            return clientConfirmSentAmount;
        }

        public void setClientConfirmSentAmount(String clientConfirmSentAmount) {
            this.clientConfirmSentAmount = clientConfirmSentAmount;
        }

        public String getClientNotified() {
            return clientNotified;
        }

        public void setClientNotified(String clientNotified) {
            this.clientNotified = clientNotified;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(String completionTime) {
            this.completionTime = completionTime;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCouponAmountUsed() {
            return couponAmountUsed;
        }

        public void setCouponAmountUsed(String couponAmountUsed) {
            this.couponAmountUsed = couponAmountUsed;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExpediteCharge() {
            return expediteCharge;
        }

        public void setExpediteCharge(String expediteCharge) {
            this.expediteCharge = expediteCharge;
        }

        public String getFileWordsOrPages() {
            return fileWordsOrPages;
        }

        public void setFileWordsOrPages(String fileWordsOrPages) {
            this.fileWordsOrPages = fileWordsOrPages;
        }

        public String getFirstCopyPrice() {
            return firstCopyPrice;
        }

        public void setFirstCopyPrice(String firstCopyPrice) {
            this.firstCopyPrice = firstCopyPrice;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getIsClientSubmitted() {
            return isClientSubmitted;
        }

        public void setIsClientSubmitted(String isClientSubmitted) {
            this.isClientSubmitted = isClientSubmitted;
        }

        public String getIsFileDeleted() {
            return isFileDeleted;
        }

        public void setIsFileDeleted(String isFileDeleted) {
            this.isFileDeleted = isFileDeleted;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(String jobStatus) {
            this.jobStatus = jobStatus;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotorizedCopies() {
            return notorizedCopies;
        }

        public void setNotorizedCopies(String notorizedCopies) {
            this.notorizedCopies = notorizedCopies;
        }

        public String getNotorizedCopyAdded() {
            return notorizedCopyAdded;
        }

        public void setNotorizedCopyAdded(String notorizedCopyAdded) {
            this.notorizedCopyAdded = notorizedCopyAdded;
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

        public String getOverdueReminderEmailSent() {
            return overdueReminderEmailSent;
        }

        public void setOverdueReminderEmailSent(String overdueReminderEmailSent) {
            this.overdueReminderEmailSent = overdueReminderEmailSent;
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

        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQuoteBy() {
            return quoteBy;
        }

        public void setQuoteBy(String quoteBy) {
            this.quoteBy = quoteBy;
        }

        public String getQuotedAmount() {
            return quotedAmount;
        }

        public void setQuotedAmount(String quotedAmount) {
            this.quotedAmount = quotedAmount;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getReminderEmail() {
            return reminderEmail;
        }

        public void setReminderEmail(String reminderEmail) {
            this.reminderEmail = reminderEmail;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String gettDollarToBeAdded() {
            return tDollarToBeAdded;
        }

        public void settDollarToBeAdded(String tDollarToBeAdded) {
            this.tDollarToBeAdded = tDollarToBeAdded;
        }

        public String gettDollarUsed() {
            return tDollarUsed;
        }

        public void settDollarUsed(String tDollarUsed) {
            this.tDollarUsed = tDollarUsed;
        }

        public String gettDollarUsedActual() {
            return tDollarUsedActual;
        }

        public void settDollarUsedActual(String tDollarUsedActual) {
            this.tDollarUsedActual = tDollarUsedActual;
        }

        public String getTotalAmountLast() {
            return totalAmountLast;
        }

        public void setTotalAmountLast(String totalAmountLast) {
            this.totalAmountLast = totalAmountLast;
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

        public String getTxLineNo() {
            return txLineNo;
        }

        public void setTxLineNo(String txLineNo) {
            this.txLineNo = txLineNo;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

    }
}
