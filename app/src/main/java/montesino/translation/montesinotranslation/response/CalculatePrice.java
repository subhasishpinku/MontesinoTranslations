package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalculatePrice {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("expedite_charge")
    @Expose
    private Integer expediteCharge;
    @SerializedName("unitPrices")
    @Expose
    private UnitPrices unitPrices;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Integer getExpediteCharge() {
        return expediteCharge;
    }

    public void setExpediteCharge(Integer expediteCharge) {
        this.expediteCharge = expediteCharge;
    }

    public UnitPrices getUnitPrices() {
        return unitPrices;
    }

    public void setUnitPrices(UnitPrices unitPrices) {
        this.unitPrices = unitPrices;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public class UnitPrices {

        @SerializedName("26536")
        @Expose
        private String _26536;
        @SerializedName("26537")
        @Expose
        private String _26537;

        public String get26536() {
            return _26536;
        }

        public void set26536(String _26536) {
            this._26536 = _26536;
        }

        public String get26537() {
            return _26537;
        }

        public void set26537(String _26537) {
            this._26537 = _26537;
        }

    }
}
