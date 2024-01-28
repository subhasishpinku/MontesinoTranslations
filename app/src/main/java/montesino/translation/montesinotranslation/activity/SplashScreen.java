package montesino.translation.montesinotranslation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.scanlibrary.SharedPreferencesManager;

import montesino.translation.montesinotranslation.R;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = SplashScreen.this.getDrawable(R.color.white);
        getWindow().setBackgroundDrawable(background);

        shared = getSharedPreferences("montesino_translation", MODE_PRIVATE);

        Log.e("USER ID",""+shared.getString("user_id", ""));

        SharedPreferencesManager.getInstance(SplashScreen.this).clearData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(shared.getString("user_id", "").equals("")) {
                    Intent intent = new Intent(SplashScreen.this, Choice.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(SplashScreen.this, NavigationDrawerActivity.class));
                }



            }
        }, 1000);
    }
}