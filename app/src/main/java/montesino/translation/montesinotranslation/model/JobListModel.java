package montesino.translation.montesinotranslation.model;

import org.json.JSONObject;

import montesino.translation.montesinotranslation.response.Jobs;

public class JobListModel {
    String  invNumber;
    String ProjectName;
    String QuotedId;
    String TranslateFrom;
    String TranslateTo;
    String amount;
    String padeType;
    String companyName;
    String assignTo;
    Jobs.Datum jsonObject;
    String jobStatus;
    String pdfLink;
    public JobListModel(String invNumber, String ProjectName, String QuotedId, String TranslateFrom, String TranslateTo, String amount, String padeType, String companyName, String assignTo, Jobs.Datum jsonObject, String jobStatus, String pdfLink) {
        this.invNumber = invNumber;
        this.ProjectName = ProjectName;
        this.QuotedId = QuotedId;
        this.TranslateFrom = TranslateFrom;
        this.TranslateTo = TranslateTo;
        this.amount = amount;
        this.padeType = padeType;
        this.companyName = companyName;
        this.assignTo = assignTo;
        this.jsonObject=jsonObject;
        this.jobStatus = jobStatus;
        this.pdfLink = pdfLink;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getQuotedId() {
        return QuotedId;
    }

    public void setQuotedId(String quotedId) {
        QuotedId = quotedId;
    }

    public String getTranslateFrom() {
        return TranslateFrom;
    }

    public void setTranslateFrom(String translateFrom) {
        TranslateFrom = translateFrom;
    }

    public String getTranslateTo() {
        return TranslateTo;
    }

    public void setTranslateTo(String translateTo) {
        TranslateTo = translateTo;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPadeType() {
        return padeType;
    }

    public void setPadeType(String padeType) {
        this.padeType = padeType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public Jobs.Datum getJsonObject() {
        return jsonObject;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public String getPdfLink() {
        return pdfLink;
    }
}
