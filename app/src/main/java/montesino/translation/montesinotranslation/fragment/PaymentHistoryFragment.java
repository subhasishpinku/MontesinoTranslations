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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.adapter.PaymentHistoryAdapter;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.JobListModel;
import montesino.translation.montesinotranslation.model.PaymentHistoryModel;
import montesino.translation.montesinotranslation.response.Jobs;
import montesino.translation.montesinotranslation.response.PaymentHistoryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentHistoryFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView PaymentHistoryList;
    RelativeLayout rv_scroll;
    SharedPreferences shared;
    String userId, account_no;
    View rootView;
    Global global;
    List<PaymentHistoryModel> paymentHistoryModels = new ArrayList<>();
    RelativeLayout payment_history;
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
        rootView = inflater.inflate(R.layout.payment_history_fragment, container, false);
        initialize();
        return rootView;
    }
    private void initialize() {
        PaymentHistoryList = (RecyclerView) rootView.findViewById(R.id.PaymentHistoryList);
        payment_history = (RelativeLayout) rootView.findViewById(R.id.payment_history);
        PaymentHistoryList.setNestedScrollingEnabled(false);
        rv_scroll = (RelativeLayout) rootView.findViewById(R.id.rv_scroll);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        global = new Global();
        paymentHistoryList(userId,account_no);
    }
    private void paymentHistoryList(String userId, String account_no) {
        System.out.println("paymentHistoryList"+userId+"  "+account_no);
        Call<PaymentHistoryResponse> paymentList = ApiClient.getInstance().getMontesinoApi().paymentHistoryResponse(userId, account_no);
        paymentList.enqueue(new Callback<PaymentHistoryResponse>() {
            @Override
            public void onResponse(Call<PaymentHistoryResponse> call, Response<PaymentHistoryResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size() ; i++) {
                        paymentHistoryModels.add(
                                new PaymentHistoryModel(
                                        response.body().getData().get(i).getItemid(),
                                        response.body().getData().get(i).getCreatedtime(),
                                        response.body().getData().get(i).getTxnid(),
                                        response.body().getData().get(i).getQuotedAmount(),
                                        response.body().getData().get(i).getPaymentAmount(),
                                        response.body().getData().get(i).getEmail(),
                                        response.body().getData().get(i).getQuotedAmount(),
                                        response.body().getData().get(i).gettDollarUsed(),
                                        response.body().getData().get(i).getPaymentStatus()));
                         System.out.println("PaymentHistoryResponse"+" "+response.body().getData().get(i).getItemid());
                    }
                    if(paymentHistoryModels.size() > 0) {
                        payment_history.setVisibility(View.GONE);
                        PaymentHistoryList.setHasFixedSize(true);
                        PaymentHistoryList.setLayoutManager(new LinearLayoutManager(getContext()));
                        PaymentHistoryAdapter adapter = new PaymentHistoryAdapter(getContext(), paymentHistoryModels);
                        PaymentHistoryList.setAdapter(adapter);
                    }else {
                        payment_history.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentHistoryResponse> call, Throwable t) {
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


