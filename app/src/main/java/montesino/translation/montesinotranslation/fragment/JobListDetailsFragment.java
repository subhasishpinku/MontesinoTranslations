package montesino.translation.montesinotranslation.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.adapter.JobListAdapter;
import montesino.translation.montesinotranslation.adapter.JobListDetailsAdapter;
import montesino.translation.montesinotranslation.model.JobListDetailsModel;
import montesino.translation.montesinotranslation.model.JobListModel;

public class JobListDetailsFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    View rootView;
    List<JobListDetailsModel> jobListDetailsModelList;
    RecyclerView JobListDetailsRecyclerView;
    TextView jobTitle,jobCode,quoteId,companyName,translatesFrom,assignedTo,translateTo,quoteAmountIds,translationDollarUsedIds,paidAmountIds,totalAmount;
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
        rootView = inflater.inflate(R.layout.job_list_details_fragement, container, false);
        jobTitle = (TextView) rootView.findViewById(R.id.jobTitle);
        jobCode = (TextView) rootView.findViewById(R.id.jobCode);
        quoteId = (TextView) rootView.findViewById(R.id.quoteId);
        companyName = (TextView) rootView.findViewById(R.id.companyName);
        translatesFrom = (TextView) rootView.findViewById(R.id.translatesFrom);
        assignedTo = (TextView) rootView.findViewById(R.id.assignedTo);
        translateTo = (TextView) rootView.findViewById(R.id.translateTo);
        quoteAmountIds = (TextView) rootView.findViewById(R.id.quoteAmountIds);
        translationDollarUsedIds = (TextView) rootView.findViewById(R.id.translationDollarUsedIds);
        paidAmountIds = (TextView) rootView.findViewById(R.id.paidAmountIds);
        totalAmount = (TextView) rootView.findViewById(R.id.totalAmount);
        jobListDetailsModelList = new ArrayList<>();
        JobListDetailsRecyclerView = (RecyclerView) rootView.findViewById(R.id.JobListDetailsRecyclerView);
        JobListDetailsRecyclerView.setNestedScrollingEnabled(false);
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
        JobListDetailsRecyclerView.setHasFixedSize(true);
        JobListDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        JobListDetailsAdapter adapter = new JobListDetailsAdapter(getActivity(), jobListDetailsModelList);
        JobListDetailsRecyclerView.setAdapter(adapter);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                JSONObject receivedJsonObject = new JSONObject(jsonString);
                // Now you have the JSON object in receivedJsonObject
                String projectName = receivedJsonObject.getString("ProjectName");
                String quotedId = receivedJsonObject.getString("QuotedId");
                String translateFrom = receivedJsonObject.getString("TranslateFrom");
                String translateTos = receivedJsonObject.getString("TranslateTo");
                String amount = receivedJsonObject.getString("amount");
                String assignTo = receivedJsonObject.getString("assignTo");
                String companyNames = receivedJsonObject.getString("companyName");
                String invNumber = receivedJsonObject.getString("invNumber");
                String padeType = receivedJsonObject.getString("padeType");
                JSONObject jsonObject = receivedJsonObject.getJSONObject("jsonObject");
                String account_no = jsonObject.getString("account_no");
                String actual_amount = jsonObject.getString("actual_amount");
                String additional_copy_price = jsonObject.getString("additional_copy_price");
                String address1 = jsonObject.getString("address1");
                String address2 = jsonObject.getString("address2");
                String amount1 = jsonObject.getString("amount");
                String auto_reminder_email_sent = jsonObject.getString("auto_reminder_email_sent");
                String city = jsonObject.getString("city");
                String client_confirm_sent_amount = jsonObject.getString("client_confirm_sent_amount");
                String client_notified = jsonObject.getString("client_notified");
                String company = jsonObject.getString("company");
                String completion_time = jsonObject.getString("completion_time");
                String country = jsonObject.getString("country");
                String coupon_amount_used = jsonObject.getString("coupon_amount_used");
                String coupon_id = jsonObject.getString("coupon_id");
                String created_at = jsonObject.getString("created_at");
                String discount = jsonObject.getString("discount");
                String email = jsonObject.getString("email");
                String expedite_charge = jsonObject.getString("expedite_charge");
                String file_words_or_pages = jsonObject.getString("file_words_or_pages");
                String first_copy_price = jsonObject.getString("first_copy_price");
                String flag = jsonObject.getString("flag");
                String id = jsonObject.getString("id");
                String invoice_number = jsonObject.getString("invoice_number");
                String ip_address = jsonObject.getString("ip_address");
                String is_client_submitted = jsonObject.getString("is_client_submitted");
                String is_file_deleted = jsonObject.getString("is_file_deleted");
                String job_name = jsonObject.getString("job_name");
                String job_status = jsonObject.getString("job_status");
                String message = jsonObject.getString("message");
                String name = jsonObject.getString("name");
                String notorized_copies = jsonObject.getString("notorized_copies");
                String notorized_copy_added = jsonObject.getString("notorized_copy_added");
                String overdue_auto_mail_sent = jsonObject.getString("overdue_auto_mail_sent");
                String overdue_auto_mail_sent_date = jsonObject.getString("overdue_auto_mail_sent_date");
                String overdue_reminder_email_sent = jsonObject.getString("overdue_reminder_email_sent");
                String page_amount = jsonObject.getString("page_amount");
                String page_or_word = jsonObject.getString("page_or_word");
                String paid_amount = jsonObject.getString("paid_amount");
                String payment = jsonObject.getString("payment");
                String pdf_link = jsonObject.getString("pdf_link");
                String pdf_permission = jsonObject.getString("pdf_permission");
                String phone = jsonObject.getString("phone");
                String quote_by = jsonObject.getString("quote_by");
                String quoted_amount = jsonObject.getString("quoted_amount");
                String register_date = jsonObject.getString("register_date");
                String reminder_email = jsonObject.getString("reminder_email");
                String show_status = jsonObject.getString("show_status");
                String state = jsonObject.getString("state");
                String status = jsonObject.getString("status");
                String t_dollar_to_be_added = jsonObject.getString("t_dollar_to_be_added");
                String t_dollar_used = jsonObject.getString("t_dollar_used");
                String t_dollar_used_actual = jsonObject.getString("t_dollar_used_actual");
                String total_amount_last = jsonObject.getString("total_amount_last");
                String translate_from = jsonObject.getString("translate_from");
                String translate_to = jsonObject.getString("translate_to");
                String tx_line_no = jsonObject.getString("tx_line_no");
                String unique_id = jsonObject.getString("unique_id");
                String user_id = jsonObject.getString("user_id");
                String zipcode = jsonObject.getString("zipcode");
                System.out.println("clickedItem_Json"+" "+zipcode);
                jobTitle.setText(projectName);
                jobCode.setText(invoice_number);
                quoteId.setText(quote_by);
                companyName.setText(companyNames);
                translatesFrom.setText(translate_from);
                translateTo.setText(translate_to);
                assignedTo.setText(assignTo);
                quoteAmountIds.setText("$"+quoted_amount);
                translationDollarUsedIds.setText("$"+t_dollar_used);
                paidAmountIds.setText("$"+paid_amount);
                float floatValue1 = Float.parseFloat(quoted_amount);
                float floatValue2 = Float.parseFloat(t_dollar_used);
                float floatValue3 = Float.parseFloat(paid_amount);
                float sum = floatValue1 + floatValue2 + floatValue3;
                String totalVal = String.valueOf(sum);
                System.out.println("Sum: " + sum);
                totalAmount.setText("$"+totalVal);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
