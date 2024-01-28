package montesino.translation.montesinotranslation.api;

import com.google.gson.JsonObject;

import montesino.translation.montesinotranslation.model.NotarizeAmountResponse;
import montesino.translation.montesinotranslation.model.ValidateCouponResponse;
import montesino.translation.montesinotranslation.model.ValidateGiftCardResponse;
import montesino.translation.montesinotranslation.request.FileUploadRequest;
import montesino.translation.montesinotranslation.response.AddCardResponse;
import montesino.translation.montesinotranslation.response.AddUserResponse;
import montesino.translation.montesinotranslation.response.BillingAddressDeleteResponse;
import montesino.translation.montesinotranslation.response.BillingAddressResponse;
import montesino.translation.montesinotranslation.response.BillingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.CalculatePrice;
import montesino.translation.montesinotranslation.response.CardListResponse;
import montesino.translation.montesinotranslation.response.CardRemoveResponse;
import montesino.translation.montesinotranslation.response.ChangePasswordResponse;
import montesino.translation.montesinotranslation.response.CountryResponse;
import montesino.translation.montesinotranslation.response.FileUploadResponse;
import montesino.translation.montesinotranslation.response.ForgotPassword;
import montesino.translation.montesinotranslation.response.Jobs;
import montesino.translation.montesinotranslation.response.LanguageResponse;
import montesino.translation.montesinotranslation.response.PaymentHistoryResponse;
import montesino.translation.montesinotranslation.response.PersonalInfoResponse;
import montesino.translation.montesinotranslation.response.PersonalInfoUpdateResponse;
import montesino.translation.montesinotranslation.response.RemoveFile;
import montesino.translation.montesinotranslation.response.RemoveUserResponse;
import montesino.translation.montesinotranslation.response.SettingsResponse;
import montesino.translation.montesinotranslation.response.ShippingAddressDeleteResponse;
import montesino.translation.montesinotranslation.response.ShippingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.ShippingResponse;
import montesino.translation.montesinotranslation.response.SignIn;
import montesino.translation.montesinotranslation.response.Signup;
import montesino.translation.montesinotranslation.response.UpdateCardResponse;
import montesino.translation.montesinotranslation.response.UserChangePassword;
import montesino.translation.montesinotranslation.response.UserManagementList;
import montesino.translation.montesinotranslation.response.UserRolePermission;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    //Settings
    @FormUrlEncoded
    @POST("site_settings")
    Call<SettingsResponse> siteSettings(
            @Field("coloumn") String coloumn
    );

    //Register
    @FormUrlEncoded
    @POST("direct-sign-up")
    Call<Signup> createAccount(
            @Field("name") String name,
            @Field("email") String email,
            @Field("country_code") String country_code,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("flag") int flag
    );
    //Login
    @FormUrlEncoded
    @POST("login")
    Call<SignIn> signInUser(
            @Field("email") String email,
            @Field("password") String password
    );

    //Forgot Password
    @FormUrlEncoded
    @POST("forgot-password")
    Call<ForgotPassword> forgotPass(
            @Field("email") String email
    );

    //Job List
    @FormUrlEncoded
    @POST("job-listing")
    Call<Jobs> jobList (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //Shipping Address
    @FormUrlEncoded
    @POST("address-info-shipping")
    Call<ShippingResponse> shippingAddress (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //Billing Address
    @FormUrlEncoded
    @POST("address-info-billing")
    Call<BillingAddressResponse> billingAddress (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );

    //Personal Information
    @FormUrlEncoded
    @POST("account-info")
    Call<PersonalInfoResponse> personalInfo (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );

    //Personal Information Update
    @FormUrlEncoded
    @POST("account-update")
    Call<PersonalInfoUpdateResponse> personalInfo_Update (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("company") String company
    );
    //All Country
    @GET("all-country")
    Call<CountryResponse> getCountry();
    //All State
    @GET("all-state")
    Call<CountryResponse> getState();
    //All Language
    @GET("all-language")
    Call<LanguageResponse> getLang();
    //Billing Address save
    @FormUrlEncoded
    @POST("billing-add")
    Call<BillingAddressSaveResponse> billingAddressSave (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no,
            @Field("address1") String address1,
            @Field("address2") String address2,
            @Field("city") String city,
            @Field("state") String state,
            @Field("country") String country,
            @Field("zipcode") String zipcode
    );
    //Delete Billing  Address
    @FormUrlEncoded
    @POST("billing-delete")
    Call<BillingAddressDeleteResponse> billingAddressDelete (
            @Field("row_id") String row_id,
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    // Shipping Address Save
    @FormUrlEncoded
    @POST("shipping-add")
    Call<ShippingAddressSaveResponse> shippingAddressSave (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no,
            @Field("address1") String address1,
            @Field("address2") String address2,
            @Field("city") String city,
            @Field("state") String state,
            @Field("country") String country,
            @Field("zipcode") String zipcode
    );
    // Shipping Address Delete
    @FormUrlEncoded
    @POST("shipping-delete")
    Call<ShippingAddressDeleteResponse> shippingAddressDelete (
            @Field("row_id") String row_id,
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    // Payment History List
    @FormUrlEncoded
    @POST("payments-list")
    Call<PaymentHistoryResponse> paymentHistoryResponse (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //Card List
    @FormUrlEncoded
    @POST("card-listing")
    Call<CardListResponse> cardList (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //Remove Card
    @FormUrlEncoded
    @POST("card-delete")
    Call<CardRemoveResponse> cardRemove (
            @Field("row_id") String row_id,
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //Add New Card
    @FormUrlEncoded
    @POST("card-add")
    Call<AddCardResponse> addCard (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no,
            @Field("card_holder") String card_holder,
            @Field("nick_name") String nick_name,
            @Field("card_number") String card_number,
            @Field("expiry_month") String expiry_month,
            @Field("expiry_year") String expiry_year,
            @Field("card_type") String card_type
    );
    //Update Card
    @FormUrlEncoded
    @POST("card-update")
    Call<UpdateCardResponse> updateCard (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no,
            @Field("card_holder") String card_holder,
            @Field("nick_name") String nick_name,
            @Field("card_number") String card_number,
            @Field("expiry_month") String expiry_month,
            @Field("expiry_year") String expiry_year,
            @Field("row_id") String row_id,
            @Field("card_type") String card_type
    );
    // Change Password
    @FormUrlEncoded
    @POST("change-password")
    Call<ChangePasswordResponse> changePasswordResponse (
            @Field("old_password") String old_password,
            @Field("new_password") String new_password,
            @Field("confirm_password") String confirm_password,
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //User Management List
    @FormUrlEncoded
    @POST("user-list")
    Call<UserManagementList> userList (
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //User Management Add
    @FormUrlEncoded
    @POST("add-user")
    Call<AddUserResponse> addUser (
            @Field("session_id") String session_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("permission") int permission
    );
    //User Management Change Password
    @FormUrlEncoded
    @POST("change-user-password")
    Call<UserChangePassword> changePassword (
            @Field("update_user_id") String update_user_id,
            @Field("session_id") String session_id,
            @Field("current_password") String current_password,
            @Field("password") String password
    );
    //User Management Change Role
    @FormUrlEncoded
    @POST("update-user-type")
    Call<UserRolePermission> changePermission (
            @Field("update_user_id") String update_user_id,
            @Field("user_id") String user_id,
            @Field("account_no") String account_no
    );
    //User Management Remove User
    @FormUrlEncoded
    @POST("delete-user")
    Call<RemoveUserResponse> removeUser (
            @Field("update_user_id") String update_user_id,
            @Field("session_id") String session_id
    );
    //Request Quote File Calculation
    @FormUrlEncoded
    @POST("calculate-trans-price")
    Call<CalculatePrice> calculatePrice (
            @Field("fileIds") String fileIds,
            @Field("translate_to") String translate_to
    );
    //Request Quote Remove File
    @FormUrlEncoded
    @POST("remove-file")
    Call<RemoveFile> removeFile (
            @Field("fileId") int fileId
    );
    //Notarize Amount
    @FormUrlEncoded
    @POST("get-notarize-amount")
    Call<NotarizeAmountResponse> notarizeAmount (
            @Field("notarize_copy") String notarize_copy
    );

    @FormUrlEncoded
    @POST("validate-gift-card")
    Call<ValidateGiftCardResponse> ValidateGiftCard (
            @Field("gift_card_code") String gift_card_code
    );
    @FormUrlEncoded
    @POST("validate-coupon")
    Call<ValidateCouponResponse> ValidateCoupon (
            @Field("email") String email,
            @Field("coupon_code") String coupon_code,
            @Field("invoice_amount") String invoice_amount
    );
}
