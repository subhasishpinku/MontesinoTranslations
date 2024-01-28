package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SettingsResponse {
    @SerializedName("words_per_page")
    @Expose
    private String wordsPerPage;

    public String getWordsPerPage() {
        return wordsPerPage;
    }

    public void setWordsPerPage(String wordsPerPage) {
        this.wordsPerPage = wordsPerPage;
    }
}
