package montesino.translation.montesinotranslation.request;

public class SignUpRequest {
    String name;
    String email;
    String country_code;
    String phone;
    String password;
    int appMode;

    public SignUpRequest(String name, String email, String country_code, String phone, String password, int appMode) {
        this.name = name;
        this.email = email;
        this.country_code = country_code;
        this.phone = phone;
        this.password = password;
        this.appMode = appMode;
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

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAppMode() {
        return appMode;
    }

    public void setAppMode(int appMode) {
        this.appMode = appMode;
    }
}
