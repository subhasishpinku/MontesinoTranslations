package montesino.translation.montesinotranslation.model;

public class CountryModel {
    String  name;
    String Id;

    public CountryModel(String name, String id) {
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }
}
