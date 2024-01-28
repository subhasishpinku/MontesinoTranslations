package montesino.translation.montesinotranslation.model;

public class JobListDetailsModel {
    String titleJobDetails;
    String subTitleJobDetails;
    String ImageJobDetails;

    public JobListDetailsModel(String titleJobDetails, String subTitleJobDetails, String imageJobDetails) {
        this.titleJobDetails = titleJobDetails;
        this.subTitleJobDetails = subTitleJobDetails;
        ImageJobDetails = imageJobDetails;
    }

    public String getTitleJobDetails() {
        return titleJobDetails;
    }

    public void setTitleJobDetails(String titleJobDetails) {
        this.titleJobDetails = titleJobDetails;
    }

    public String getSubTitleJobDetails() {
        return subTitleJobDetails;
    }

    public void setSubTitleJobDetails(String subTitleJobDetails) {
        this.subTitleJobDetails = subTitleJobDetails;
    }

    public String getImageJobDetails() {
        return ImageJobDetails;
    }

    public void setImageJobDetails(String imageJobDetails) {
        ImageJobDetails = imageJobDetails;
    }
}
