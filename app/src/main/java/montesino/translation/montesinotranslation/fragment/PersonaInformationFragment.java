package montesino.translation.montesinotranslation.fragment;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.JobListModel;
import montesino.translation.montesinotranslation.response.Jobs;
import montesino.translation.montesinotranslation.response.PersonalInfoResponse;
import montesino.translation.montesinotranslation.response.PersonalInfoUpdateResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaInformationFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    View rootView;
    LinearLayout btn_edit_acc,btn_submit;
    TextInputLayout fullNameId,emailId,phoneId,accountId,rewardAmountId,companyID;
    TextInputEditText fullNameIds,emailIds,phoneIds,accountIds,rewardAmountIds,companyIDs;
    Global global;
    SharedPreferences shared;
    String userId, account_no;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        backButtonHandler = (BackButtonHandlerInterface) activity;
        backButtonHandler.addBackClickListener(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.personal_information_fragment, container, false);
        initialize();
        return rootView;
    }
    private void initialize() {
        btn_edit_acc = (LinearLayout) rootView.findViewById(R.id.btn_edit_acc);
        btn_submit = (LinearLayout) rootView.findViewById(R.id.btn_submit);
        fullNameId = (TextInputLayout) rootView.findViewById(R.id.fullNameId);
        emailId = (TextInputLayout) rootView.findViewById(R.id.emailId);
        phoneId = (TextInputLayout) rootView.findViewById(R.id.phoneId);
        accountId = (TextInputLayout) rootView.findViewById(R.id.accountId);
        rewardAmountId = (TextInputLayout) rootView.findViewById(R.id.rewardAmountId);
        companyID = (TextInputLayout) rootView.findViewById(R.id.rewardAmountId);
        //
        fullNameIds = (TextInputEditText) rootView.findViewById(R.id.fullNameIds);
        emailIds = (TextInputEditText) rootView.findViewById(R.id.emailIds);
        phoneIds = (TextInputEditText) rootView.findViewById(R.id.phoneIds);
        accountIds = (TextInputEditText) rootView.findViewById(R.id.accountIds);
        rewardAmountIds = (TextInputEditText) rootView.findViewById(R.id.rewardAmountIds);
        companyIDs = (TextInputEditText) rootView.findViewById(R.id.companyIDs);
        btn_edit_acc.setVisibility(View.VISIBLE);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        global = new Global();
        fetch_personalInformation(userId,account_no);
        fullNameIds.setFocusable(false);
        emailIds.setFocusable(false);
        phoneIds.setFocusable(false);
        accountIds.setFocusable(false);
        rewardAmountIds.setFocusable(false);
        companyIDs.setFocusable(false);
        btn_edit_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameIds.setFocusableInTouchMode(true);
                phoneIds.setFocusableInTouchMode(true);
                btn_edit_acc.setVisibility(View.GONE);
                btn_submit.setVisibility(View.VISIBLE);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullNameIds.getText().toString().equals("")) {
                    fullNameIds.setError(getResources().getString(R.string.enter_full_name));
                    fullNameIds.requestFocus();
                }else if (phoneIds.getText().toString().equals("")) {
                    phoneIds.setError(getResources().getString(R.string.enter_phone_number));
                    phoneIds.requestFocus();
                }else {
                    if (global.isNetworkAvailable(getContext())) {
                        personal_Information_update(userId,account_no,fullNameIds.getText().toString(),phoneIds.getText().toString(),companyIDs.getText().toString());
                    } else {
                        Toast.makeText(getContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void fetch_personalInformation(String userId, String account_no) {
        Call<PersonalInfoResponse> jobList = ApiClient.getInstance().getMontesinoApi().personalInfo(userId, account_no);
        jobList.enqueue(new Callback<PersonalInfoResponse>() {
            @Override
            public void onResponse(Call<PersonalInfoResponse> call, Response<PersonalInfoResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    fullNameIds.setText(response.body().getData().getName());
                    emailIds.setText(response.body().getData().getEmail());
                    phoneIds.setText(response.body().getData().getPhone());
                    accountIds.setText(response.body().getData().getAccountNo());
                    rewardAmountIds.setText(response.body().getData().getRewardCredit());
                    companyIDs.setText(response.body().getData().getCompany());
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonalInfoResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }

    private void personal_Information_update(String userId, String account_no,String fullName,String phone,String company_Name) {
        System.out.println("personal_Information_update"+" "+userId+" "+account_no+" "+fullName+" "+phone+" "+company_Name);
        Call<PersonalInfoUpdateResponse> jobList = ApiClient.getInstance().getMontesinoApi().personalInfo_Update(userId, account_no,fullName,phone,company_Name);
        jobList.enqueue(new Callback<PersonalInfoUpdateResponse>() {
            @Override
            public void onResponse(Call<PersonalInfoUpdateResponse> call, Response<PersonalInfoUpdateResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PersonalInfoUpdateResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        backButtonHandler.removeBackClickListener(this);
        backButtonHandler = null;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onBackClick() {
        if (doubleBackToExitPressedOnce) {
            // super.onBackPressed();
            getActivity().finishAffinity();
            System.exit(0);
            return false;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getContext(), "Please again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 2000);
        return true;
    }
}