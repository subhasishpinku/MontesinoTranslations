package montesino.translation.montesinotranslation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.io.IOException;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.response.Signup;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account extends AppCompatActivity {

    LinearLayout back, createAccount;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

            "\\@" +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

            "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    String newEncrypt;
    EditText name, emailAddress, phoneNumber, accountPassword, cnf_Password;
    ImageView eye_btn, eye_btn_cnf;
    int appMode = 1;
    CountryCodePicker cpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = Account.this.getDrawable(R.drawable.screen_header);
        getWindow().setBackgroundDrawable(background);

        initialize();
    }

    private void initialize() {
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        emailAddress = findViewById(R.id.emailAddress);
        phoneNumber = findViewById(R.id.phoneNumber);
        accountPassword = findViewById(R.id.accountPassword);
        cnf_Password = findViewById(R.id.cnf_Password);
        eye_btn = findViewById(R.id.eye_btn);
        eye_btn_cnf = findViewById(R.id.eye_btn_cnf);
        createAccount = findViewById(R.id.createAccount);
        cpp = findViewById(R.id.cpp);

        eye_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePassword("Password");
            }
        });

        eye_btn_cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePassword("Confirm Password");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectBack();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if(name.getText().toString().equals("")) {
            name.setError(getResources().getString(R.string.sign_up_validation_your_name));
        } else if (emailAddress.getText().toString().equals("")) {
            emailAddress.setError(getResources().getString(R.string.sign_up_validation_your_email));
        } else if(!emailAddress.getText().toString().matches(emailPattern)) {
            emailAddress.setError(getResources().getString(R.string.sign_up_validation_your_email_valid));
        } else if (phoneNumber.getText().toString().equals("")) {
            phoneNumber.setError(getResources().getString(R.string.sign_up_validation_your_phone));
        } else if (phoneNumber.getText().toString().length() < 10) {
            phoneNumber.setError(getResources().getString(R.string.sign_up_validation_your_phone_valid));
        } else if (accountPassword.getText().toString().equals("")) {
            eye_btn.setVisibility(View.GONE);
            accountPassword.setError(getResources().getString(R.string.sign_up_validation_your_password));
        } else if (accountPassword.getText().toString().length() < 6) {
            eye_btn.setVisibility(View.GONE);
            accountPassword.setError(getResources().getString(R.string.validation_your_password_short));
        } else if(cnf_Password.getText().toString().equals("")) {
            eye_btn_cnf.setVisibility(View.GONE);
            cnf_Password.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password));
        } else if (cnf_Password.getText().toString().length() < 6) {
            eye_btn_cnf.setVisibility(View.GONE);
            cnf_Password.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password_short));
        } else if(accountPassword.getText().toString().equals(cnf_Password.getText().toString())) {
            eye_btn_cnf.setVisibility(View.VISIBLE);
            eye_btn.setVisibility(View.VISIBLE);
            register(name.getText().toString(), emailAddress.getText().toString(), cpp.getSelectedCountryCodeWithPlus(),
                    phoneNumber.getText().toString(), accountPassword.getText().toString(), appMode);

        } else {
            eye_btn_cnf.setVisibility(View.GONE);
            cnf_Password.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password_wrong));
        }
    }

//    private void callCurreny() {
//        Call<ResponseBody> responseBodyCall = ApiClient.getInstance().getMontesinoApi().getCurrency();
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    Log.e("RESPONSE Currency",""+response.body().string());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//    }

    public void register(String name, String email, String country_code, String phone, String password, int appMode){
        Call<Signup> signupCall = ApiClient.getInstance().getMontesinoApi().createAccount(name, email, country_code, phone, password, appMode);
        signupCall.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {
                if(response.body().getStatusCode() == 200) {
                    SharedPreferences loginRef = getSharedPreferences("montesino_translation", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginRef.edit();
                    editor.putString("user_id",response.body().getData().getId());
                    editor.putString("user_name",response.body().getData().getName());
                    editor.putString("pass_change",response.body().getData().getIsChangedPassword());
                    editor.commit();

                    startActivity(new Intent(Account.this, NavigationDrawerActivity.class));
                } else {
                    Toast.makeText(Account.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {
                Log.e("RESPONSE DATA FAILED","BYE"+t.getLocalizedMessage());
                Log.e("RESPONSE DATA FAILED ALT","TATA"+t.getMessage());

            }
        });

    }


    private void togglePassword(String type) {
        if(type.equals("Password")) {
            if(accountPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                accountPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.eye_close);
            } else {
                accountPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.eye_open);
            }
        } else {
            if(cnf_Password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                cnf_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_cnf.setImageResource(R.drawable.eye_close);
            } else {
                cnf_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_cnf .setImageResource(R.drawable.eye_open);
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void redirectBack() {
        startActivity(new Intent(Account.this, Choice.class));
    }
}