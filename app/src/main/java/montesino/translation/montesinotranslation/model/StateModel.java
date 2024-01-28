package montesino.translation.montesinotranslation.model;


public class StateModel {
    String  name;
    String Id;

    public StateModel(String name, String id) {
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
