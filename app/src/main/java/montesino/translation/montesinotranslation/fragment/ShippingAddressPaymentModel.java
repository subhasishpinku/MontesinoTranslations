package montesino.translation.montesinotranslation.fragment;

public class ShippingAddressPaymentModel {
    String addressTitle;
    String address;
    String city;
    String country;
    String state;
    String pinCode;
    String rowIds;
    private boolean isChecked;


    public ShippingAddressPaymentModel(String addressTitle, String address, String city, String country, String state, String pinCode, String rowIds) {
        this.addressTitle = addressTitle;
        this.address = address;
        this.city = city;
        this.country = country;
        this.state = state;
        this.pinCode = pinCode;
        this.rowIds = rowIds;
        this.isChecked = false;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getRowIds() {
        return rowIds;
    }

    public void setRowIds(String rowIds) {
        this.rowIds = rowIds;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
