package montesino.translation.montesinotranslation.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import montesino.translation.montesinotranslation.adapter.JobListDetailsAdapter;
import montesino.translation.montesinotranslation.model.JobListDetailsModel;

public class TrackorderFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    View rootView;
    RecyclerView trackOrderRecyclerView;
    List<JobListDetailsModel> jobListDetailsModelList;
    RelativeLayout trac_order;
    EditText searchBy;
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
        rootView = inflater.inflate(R.layout.track_order_fragement, container, false);
        trackOrderRecyclerView = (RecyclerView)rootView.findViewById(R.id.trackOrderRecyclerView);
        trac_order = (RelativeLayout)rootView.findViewById(R.id.trac_order);
        trackOrderRecyclerView.setVisibility(View.GONE);
        trac_order.setVisibility(View.VISIBLE);
        searchBy = (EditText) rootView.findViewById(R.id.searchBy);
        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("TextCount"+" "+count);
                if (count>=3){
                    trackOrderRecyclerView.setVisibility(View.VISIBLE);
                    trac_order.setVisibility(View.GONE);

                }else {
                    trackOrderRecyclerView.setVisibility(View.GONE);
                    trac_order.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        jobListDetailsModelList = new ArrayList<>();
        trackOrderRecyclerView.setNestedScrollingEnabled(false);
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Submit on TE",
                        "In Progress",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "TM name",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Post Job",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Award Job",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Where was it awarded",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Proofreading",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Send to CS",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Send to Client",
                        "No Completed",""
                ));
        jobListDetailsModelList.add(
                new JobListDetailsModel(
                        "Notarized Conv Sent?",
                        "No Completed",""
                ));
        trackOrderRecyclerView.setHasFixedSize(true);
        trackOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        JobListDetailsAdapter adapter = new JobListDetailsAdapter(getActivity(), jobListDetailsModelList);
        trackOrderRecyclerView.setAdapter(adapter);
        return rootView;
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