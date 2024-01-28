package montesino.translation.montesinotranslation.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import montesino.translation.montesinotranslation.adapter.JobListAdapter;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.model.JobListModel;
import montesino.translation.montesinotranslation.response.Jobs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobListFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView JobListRecyclerView;
    View rootView;
    List<JobListModel> jobListModels = new ArrayList<>();
    SharedPreferences shared;
    String userId, account_no;
    RelativeLayout rv_scroll;
    RelativeLayout job_list;
    Dialog progressDialog;
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
        rootView = inflater.inflate(R.layout.job_list_fragement, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data = bundle.getString("key");
            System.out.println("DataPassing"+data);
        }

        initialize();
        return rootView;
    }
    private void initialize() {
        JobListRecyclerView = (RecyclerView) rootView.findViewById(R.id.JobListRecyclerView);
        JobListRecyclerView.setNestedScrollingEnabled(false);
        rv_scroll = (RelativeLayout) rootView.findViewById(R.id.rv_scroll);
        job_list = (RelativeLayout) rootView.findViewById(R.id.job_list);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        System.out.println("User ID" +userId + account_no);
        progressDialog = new Dialog(getContext());
        progressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog.setContentView(R.layout.progress);
        progressDialog.setCancelable(false);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        getJobList(userId, account_no);
    }

    private void getJobList(String userId, String account_no) {
        progressDialog.show();
        Call<Jobs> jobList = ApiClient.getInstance().getMontesinoApi().jobList(userId, account_no);
        jobList.enqueue(new Callback<Jobs>() {
            @Override
            public void onResponse(Call<Jobs> call, Response<Jobs> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size() ; i++) {
                        jobListModels.add(
                                new JobListModel(
                                        response.body().getData().get(i).getInvoiceNumber(),
                                        response.body().getData().get(i).getJobName(),
                                        response.body().getData().get(i).getQuoteBy(),
                                        response.body().getData().get(i).getTranslateFrom(),
                                        response.body().getData().get(i).getTranslateTo(),
                                        response.body().getData().get(i).getAmount(),
                                        response.body().getData().get(i).getJobStatus(),
                                        response.body().getData().get(i).getCompany(),
                                        response.body().getData().get(i).getName(),
                                        response.body().getData().get(i),
                                        response.body().getData().get(i).getJobStatus(),
                                        response.body().getData().get(i).getPdfLink()));
                    }
                    if(jobListModels.size() > 0) {
                        job_list.setVisibility(View.GONE);
                        JobListRecyclerView.setHasFixedSize(true);
                        JobListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        JobListAdapter adapter = new JobListAdapter(getActivity(), jobListModels);
                        JobListRecyclerView.setAdapter(adapter);
                    } else {
                        job_list.setVisibility(View.VISIBLE);
                    }
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Jobs> call, Throwable t) {
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
