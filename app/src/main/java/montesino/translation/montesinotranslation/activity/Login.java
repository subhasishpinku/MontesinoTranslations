package montesino.translation.montesinotranslation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.response.ForgotPassword;
import montesino.translation.montesinotranslation.response.SignIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView forgot_pass, new_user;
    ImageView eye_btn;
    EditText accountPassword, emailAddress, forgotPassEmail;
    LinearLayout login, back, forgotPassNext;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

            "\\@" +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

            "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    BottomSheetDialog sheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = Login.this.getDrawable(R.drawable.screen_header);
        getWindow().setBackgroundDrawable(background);


        initialize();
    }

    private void initialize() {
        forgot_pass = findViewById(R.id.forgot_pass);
        new_user = findViewById(R.id.sign_up);
        eye_btn = findViewById(R.id.eye_btn);
        emailAddress = findViewById(R.id.emailAddress);
        accountPassword = findViewById(R.id.accountPassword);
        login = findViewById(R.id.login);
        back = findViewById(R.id.back);



        forgot_pass.setText(Html.fromHtml("<u>"+getResources().getString(R.string.forgot_password)+"</u>"));
        new_user.setText(Html.fromHtml("<u>"+getResources().getString(R.string.sign_up)+"</u>"));
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Account.class);
                startActivity(intent);
            }
        });

        eye_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePassword("Password");
            }
        });
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectBack();
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSheet();
            }
        });
    }

    private void openSheet() {
        sheetDialog = new BottomSheetDialog(Login.this, R.style.BottomSheetStyle);
        View forgotPass = getLayoutInflater().inflate(R.layout.forgot_passowrd, null);

        forgotPassEmail = forgotPass.findViewById(R.id.forgotPassEmailAddress);
        forgotPassNext = forgotPass.findViewById(R.id.next);

        if(!emailAddress.getText().toString().equals("") && emailAddress.getText().toString().matches(emailPattern)) {
            forgotPassEmail.setText(emailAddress.getText().toString());
        } else {
            forgotPassEmail.setText("");
        }

        forgotPassNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForgotPass("mailVerify");
            }
        });
        sheetDialog.setContentView(forgotPass);
        sheetDialog.show();
    }

    private void validateForgotPass(String mode) {
        //Forgot Password Email Verify Validation
        if(mode.equals("mailVerify")) {
            if(forgotPassEmail.getText().toString().equals("")) {
                forgotPassEmail.setError(getResources().getString(R.string.forgot_pass_email_validation));
                forgotPassEmail.requestFocus();
            } else if (!forgotPassEmail.getText().toString().matches(emailPattern)) {
                forgotPassEmail.setError(getResources().getString(R.string.forgot_pass_email_validation_email_valid));
                forgotPassEmail.requestFocus();
            } else {
                requestPass(forgotPassEmail.getText().toString());
            }
        }
    }

    private void requestPass(String forgotEmail) {
        Call<ForgotPassword> forgetPass = ApiClient.getInstance().getMontesinoApi().forgotPass(forgotEmail);
        forgetPass.enqueue(new Callback<ForgotPassword>() {
            @Override
            public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(Login.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(Login.this, "Failed:-"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.e("NEW Pass", ""+response.body().getData().getPassword());
            }

            @Override
            public void onFailure(Call<ForgotPassword> call, Throwable t) {

            }
        });
    }

    private void validate() {
        if(emailAddress.getText().toString().equals("")) {
            emailAddress.setError(getResources().getString(R.string.log_in_validation_your_email));
        } else if(!emailAddress.getText().toString().matches(emailPattern)) {
            emailAddress.setError(getResources().getString(R.string.log_in_validation_your_email_valid));
        } else if (accountPassword.getText().toString().equals("")) {
            eye_btn.setVisibility(View.GONE);
            accountPassword.setError(getResources().getString(R.string.log_in));
        } else if (accountPassword.getText().toString().length() < 6) {
            eye_btn.setVisibility(View.GONE);
            accountPassword.setError(getResources().getString(R.string.validation_your_password_short));
        } else {
            callLogin(emailAddress.getText().toString(), accountPassword.getText().toString());
        }
    }

    private void callLogin(String email, String password) {
        Call<SignIn> signIn = ApiClient.getInstance().getMontesinoApi().signInUser(email, password);
        signIn.enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                if(response.body().getStatusCode() == 200) {
                    SharedPreferences loginRef = getSharedPreferences("montesino_translation", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginRef.edit();
                    editor.putString("user_id",response.body().getData().getId());
                    editor.putString("user_name",response.body().getData().getName());
                    editor.putString("pass_change",response.body().getData().getIsChangedPassword());
                    editor.putString("account_no",response.body().getData().getAccountNo());
                    editor.putString("email",response.body().getData().getEmail());
                    editor.commit();
                    startActivity(new Intent(Login.this, NavigationDrawerActivity.class));
                } else {
                    Toast.makeText(Login.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {

            }
        });
    }

    private void togglePassword(String type) {
        if (accountPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
            accountPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            eye_btn.setImageResource(R.drawable.eye_close);
        } else {
            accountPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            eye_btn.setImageResource(R.drawable.eye_open);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        redirectBack();
    }

    private void redirectBack() {
        startActivity(new Intent(Login.this, Choice.class));
    }
}