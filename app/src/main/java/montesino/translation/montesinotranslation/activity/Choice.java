package montesino.translation.montesinotranslation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.fragment.JobListDetailsFragment;

public class Choice extends AppCompatActivity {
    LinearLayout sign_up_layout, log_in_layout, quote_layout, self_check_out_layout;
    Intent redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = Choice.this.getDrawable(R.color.white);
        getWindow().setBackgroundDrawable(background);
        initialize();
    }
    private void initialize() {
        sign_up_layout = findViewById(R.id.sign_up_layout);
        log_in_layout = findViewById(R.id.log_in_layout);
        quote_layout = findViewById(R.id.quote_layout);
        self_check_out_layout = findViewById(R.id.self_check_out_layout);
        sign_up_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("sign_up",v);
            }
        });
        log_in_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("log_in",v);
            }
        });

        quote_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("quote",v);
            }
        });

        self_check_out_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callPage("quote",v);
            }
        });
    }
    private void callPage(String mode,View v) {
        if(mode.equals("sign_up")) {
            redirect = new Intent(Choice.this, Account.class);
        } else if (mode.equals("log_in")) {
            redirect = new Intent(Choice.this, Login.class);
        } else if (mode.equals("quote")) {
            redirect = new Intent(Choice.this,RequestQuote.class);
        }
        startActivity(redirect);
    }
}