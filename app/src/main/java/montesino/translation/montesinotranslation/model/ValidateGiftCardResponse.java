package montesino.translation.montesinotranslation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateGiftCardResponse {
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("coupon_id")
        @Expose
        private Integer couponId;
        @SerializedName("coupon_amount")
        @Expose
        private Double couponAmount;
        @SerializedName("msg")
        @Expose
        private String msg;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getCouponId() {
            return couponId;
        }

        public void setCouponId(Integer couponId) {
            this.couponId = couponId;
        }

        public Double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(Double couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
}
