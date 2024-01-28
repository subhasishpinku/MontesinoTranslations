package montesino.translation.montesinotranslation.model;

public class UserManagementModel {
    String id;
    String account_no;
    String email;
    String companyName;
    String name;
    String is_company_admin;
    String is_company_admin_me;

    public UserManagementModel(String id, String account_no, String email, String companyName, String name, String is_company_admin, String is_company_admin_me) {
        this.id = id;
        this.account_no = account_no;
        this.email = email;
        this.companyName = companyName;
        this.name = name;
        this.is_company_admin = is_company_admin;
        this.is_company_admin_me = is_company_admin_me;
    }

    public String getId() {
        return id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getIs_company_admin() {
        return is_company_admin;
    }

    public String getIs_company_admin_me() {
        return is_company_admin_me;
    }
}
