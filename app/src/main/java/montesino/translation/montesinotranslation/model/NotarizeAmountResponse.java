package montesino.translation.montesinotranslation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotarizeAmountResponse {
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("notarize_copy")
        @Expose
        private String notarizeCopy;
        @SerializedName("notarize_amount")
        @Expose
        private Integer notarizeAmount;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getNotarizeCopy() {
            return notarizeCopy;
        }

        public void setNotarizeCopy(String notarizeCopy) {
            this.notarizeCopy = notarizeCopy;
        }

        public Integer getNotarizeAmount() {
            return notarizeAmount;
        }

        public void setNotarizeAmount(Integer notarizeAmount) {
            this.notarizeAmount = notarizeAmount;
        }

    }

