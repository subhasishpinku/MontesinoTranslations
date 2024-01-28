package montesino.translation.montesinotranslation.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.fragment.BackButtonHandlerInterface;
import montesino.translation.montesinotranslation.fragment.BillingAddressFragment;
import montesino.translation.montesinotranslation.fragment.CreditDebitFragment;
import montesino.translation.montesinotranslation.fragment.JobListFragment;
import montesino.translation.montesinotranslation.fragment.FragmentDrawer;
import montesino.translation.montesinotranslation.fragment.MyAccountFragment;
import montesino.translation.montesinotranslation.fragment.OnBackClickListener;
import montesino.translation.montesinotranslation.fragment.OrderNowFragment;
import montesino.translation.montesinotranslation.fragment.PaymentHistoryFragment;
import montesino.translation.montesinotranslation.fragment.PersonaInformationFragment;
import montesino.translation.montesinotranslation.fragment.ShippingAddressFragment;
import montesino.translation.montesinotranslation.fragment.SubmitAQuoteFragment;
import montesino.translation.montesinotranslation.fragment.TrackorderFragment;
import montesino.translation.montesinotranslation.fragment.UserManagementFragment;
import montesino.translation.montesinotranslation.global.Consts;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.response.ChangePasswordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationDrawerActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, BackButtonHandlerInterface{
    private static String TAG = NavigationDrawerActivity.class.getSimpleName();
    private Toolbar mToolbar;
    ImageView openDrawer;
    private FragmentDrawer drawerFragment;
    private SharedPreferences sp;
    private ArrayList<WeakReference<OnBackClickListener>> backClickListenersList = new ArrayList<>();
    BottomSheetDialog sheetDialog;
//    TextInputLayout currentPassIds,passIds,RepassIds;
    EditText currentPassText,passText,RepassText;
    ImageView eye_btn_current, eye_btn, eye_btn_cnf;
    LinearLayout passUpdate;
    Global global;
    SharedPreferences shared;
    String userId, account_no;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.navigation_drawer_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sp  =   getSharedPreferences(Consts.SP_NAME, Context.MODE_PRIVATE);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
        displayView(Consts.JOBS);
        shared = getApplicationContext().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        global = new Global();
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("PRE_VAL","Montesinos");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // ignore orientation/keyboard change
        Log.i("tag", "On config changed");
        super.onConfigurationChanged(newConfig);

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
        System.out.println("DisplayView"+" "+"New"+position);
    }

    private void displayView(int position) {
        System.out.println("DisplayView"+" "+position);
        Fragment fragment = null;
        String title = getResources().getString(R.string.app_name);
        switch (position) {
            case Consts.ORDERNOW:
                title = getResources().getString(R.string.order_now);
                fragment = new OrderNowFragment();
                break;
            case Consts.SUBMITAQUOTE:
                title = getResources().getString(R.string.submitAQuote);
                fragment = new SubmitAQuoteFragment();
                break;
            case Consts.JOBS:
                title = getResources().getString(R.string.job_list);
              //  fragment = new JobListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key", "value");
                fragment = new JobListFragment();
                fragment.setArguments(bundle);
                break;
            case Consts.MYACCOUNT:
                title = getResources().getString(R.string.my_Account);
                fragment = new MyAccountFragment();
                break;
            case Consts.TRACKORDER:
                title = getResources().getString(R.string.track_Order);
                fragment = new TrackorderFragment();
                break;
            case Consts.USERMANAGEMENT:
                title = getResources().getString(R.string.user_Management);
                fragment = new UserManagementFragment();
                break;
            case Consts.SIGNOUT:
                ask_before_logout();
                break;
            case Consts.PERSONAL_INFORMATION:
                title = getResources().getString(R.string.Personal_Information);
                fragment = new PersonaInformationFragment();
                break;
            case Consts.CREDIT_DEBIT_CARDS:
                title = getResources().getString(R.string.credit_debitcards);
                fragment = new CreditDebitFragment();
                break;
            case Consts.SHIPPING_ADDRESS:
                title = getResources().getString(R.string.shipping_address);
                fragment = new ShippingAddressFragment();
                break;
            case Consts.BILLING_ADDRESS:
                title = getResources().getString(R.string.billing_address);
                fragment = new BillingAddressFragment();
                break;
            case Consts.PAYMENT_HISTORY:
                title = getResources().getString(R.string.payment_history);
                fragment = new PaymentHistoryFragment();
                break;
            case Consts.CHANGE_PASSWORD:
//                title = getResources().getString(R.string.change_password);
//                fragment = new ChangePasswordFragment();
                  ChangePassword();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            setTitle(title);
            System.out.println("container_body"+" "+title);
        }else {
            System.out.println("container_body_else"+" "+title);
        }
    }

    public  void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    public void ask_before_logout() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(NavigationDrawerActivity.this);
        //builder.setTitle("Confirmation!");
        builder.setMessage(getResources().getString(R.string.you_sure_that_you_want_to_logout));
        builder.setPositiveButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // Do nothing, but close the dialog
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences loginRef = getSharedPreferences("montesino_translation", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginRef.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(NavigationDrawerActivity.this,Login.class);
                startActivity(intent);
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }
    private void ChangePassword() {
        sheetDialog = new BottomSheetDialog(NavigationDrawerActivity.this, R.style.BottomSheetStyle);
        View changePass = getLayoutInflater().inflate(R.layout.change_password, null);

        currentPassText  = changePass.findViewById(R.id.currentPassIds);
        passText  = changePass.findViewById(R.id.passIds);
        RepassText  = changePass.findViewById(R.id.RepassIds);
        passUpdate = changePass.findViewById(R.id.passUpdate);
        eye_btn_current = changePass.findViewById(R.id.eye_btn_current);
        eye_btn = changePass.findViewById(R.id.eye_btn);
        eye_btn_cnf = changePass.findViewById(R.id.eye_btn_cnf);

        eye_btn_current.setOnClickListener(v -> {
            togglePassword("Current");
        });

        eye_btn.setOnClickListener(v -> {
            togglePassword("Password");
        });

        eye_btn_cnf.setOnClickListener(v -> {
            togglePassword("Confirm");
        });

        passUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPassText.getText().toString().equals("")) {
                    currentPassText.setError(getResources().getString(R.string.oldPassword));
                    currentPassText.requestFocus();
                }else if(passText.getText().toString().equals("")) {
                    passText.setError(getResources().getString(R.string.enter_your_new_password));
                    passText.requestFocus();
                }else if(passText.getText().toString().length() < 6) {
                    passText.setError(getResources().getString(R.string.new_password_must_be_6_character_long));
                    passText.requestFocus();
                }else if(RepassText.getText().toString().equals("")) {
                    RepassText.setError(getResources().getString(R.string.re_enter_new_password));
                    RepassText.requestFocus();
                }else if(passText.getText().toString().equals(RepassText.getText().toString())) {
                    if(global.isNetworkAvailable(NavigationDrawerActivity.this)) {
                        changePassword(currentPassText.getText().toString(),passText.getText().toString(),RepassText.getText().toString(),userId,account_no);
                    } else {
                        String please_Check_Your_Internet_Connection = getResources().getString(R.string.please_Check_Your_Internet_Connection);
                        Toast.makeText(NavigationDrawerActivity.this, please_Check_Your_Internet_Connection, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    RepassText.setError(getResources().getString(R.string.confirm_password_mismatch));
                    RepassText.requestFocus();
                }
            }
        });

        sheetDialog.setContentView(changePass);
        sheetDialog.show();
    }

    private void togglePassword(String type) {
        if(type.equals("Password")) {
            if(passText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                passText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                passText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.user_add_pass_eye_open);
            }
        } else if (type.equals("Current")) {
            if(currentPassText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                currentPassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_current.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                currentPassText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_current.setImageResource(R.drawable.user_add_pass_eye_open);
            }
        } else {
            if(RepassText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                RepassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_cnf.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                RepassText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_cnf .setImageResource(R.drawable.user_add_pass_eye_open);
            }
        }
    }

    private void changePassword(String oldPass, String newPass, String rePass, String userId,String account_no) {
        Call<ChangePasswordResponse> passwordChange = ApiClient.getInstance().getMontesinoApi().changePasswordResponse(oldPass, newPass,rePass,userId,account_no);
        passwordChange.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Inside onResume");
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("Inside onStart");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("Inside onReStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("Inside onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("Inside onStop");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }

    @Override
    public void addBackClickListener(OnBackClickListener onBackClickListener) {
        backClickListenersList.add(new WeakReference<>(onBackClickListener));
    }

    @Override
    public void removeBackClickListener(OnBackClickListener onBackClickListener) {
        for (Iterator<WeakReference<OnBackClickListener>> iterator = backClickListenersList.iterator();
             iterator.hasNext();){
            WeakReference<OnBackClickListener> weakRef = iterator.next();
            if (weakRef.get() == onBackClickListener){
                iterator.remove();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(!fragmentsBackKeyIntercept()){
            super.onBackPressed();
        }
    }

    private boolean fragmentsBackKeyIntercept() {
        boolean isIntercept = false;
        for (WeakReference<OnBackClickListener> weakRef : backClickListenersList) {
            OnBackClickListener onBackClickListener = weakRef.get();
            if (onBackClickListener != null) {
                boolean isFragmIntercept = onBackClickListener.onBackClick();
                if (!isIntercept) isIntercept = isFragmIntercept;
            }
        }
        return isIntercept;
    }
}