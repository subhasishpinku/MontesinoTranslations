package montesino.translation.montesinotranslation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateCouponResponse {
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("coupon_id")
        @Expose
        private String couponId;
        @SerializedName("coupon_amount")
        @Expose
        private Double couponAmount;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public Double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(Double couponAmount) {
            this.couponAmount = couponAmount;
        }


}
