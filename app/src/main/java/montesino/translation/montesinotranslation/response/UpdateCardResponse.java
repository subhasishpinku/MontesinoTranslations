package montesino.translation.montesinotranslation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCardResponse {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
