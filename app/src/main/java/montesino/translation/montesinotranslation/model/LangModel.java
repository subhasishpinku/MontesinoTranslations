package montesino.translation.montesinotranslation.model;

public class LangModel {
    String id;
    String langName;

    public LangModel(String id, String langName) {
        this.id = id;
        this.langName = langName;
    }

    public String getId() {
        return id;
    }

    public String getLangName() {
        return langName;
    }
}
