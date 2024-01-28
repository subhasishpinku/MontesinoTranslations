package montesino.translation.montesinotranslation.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;
import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.activity.PaymentOptionActivity;
import montesino.translation.montesinotranslation.activity.PdfView;
import montesino.translation.montesinotranslation.fragment.JobListDetailsFragment;
import montesino.translation.montesinotranslation.model.JobListModel;
public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobListViewHolder> {
    private Context mCtx;
    private List<JobListModel> jobListModelList;
    BottomSheetDialog sheetDialog;
    LinearLayout pay, pdf, delete, cancel;

    public JobListAdapter(Context mCtx, List<JobListModel> jobListModelList) {
        this.mCtx = mCtx;
        this.jobListModelList = jobListModelList;
    }
    @Override
    public JobListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.job_list_adapter, null);
        return new JobListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(JobListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //getting the product of the specified position
        JobListModel jobListModels = jobListModelList.get(position);
        holder.countryName.setText(jobListModels.getInvNumber());
        holder.jobName.setText(jobListModels.getProjectName());
        holder.companyName.setText(jobListModels.getCompanyName());
        holder.quotedId_text.setText(jobListModels.getAssignTo());
        holder.translateFrom_text.setText(jobListModels.getTranslateFrom());
        holder.translateTo_text.setText(jobListModels.getTranslateTo());
        holder.amounts.setText("$" + jobListModels.getAmount());
        holder.completed_paidID.setText(jobListModels.getPadeType());
        if (jobListModels.getPadeType().equals("Quote")){
            holder.completed_paid_rv.setBackgroundResource(R.drawable.bg_completed_paid_red);
            holder.tickImage_replace.setBackgroundResource(R.drawable.circle_bg);
            holder.pdfId.setBackgroundResource(R.drawable.pdf_icon);
            holder.amounts.setTextColor(ContextCompat.getColor(mCtx,R.color.amount_color));
        } else {
            holder.completed_paid_rv.setBackgroundResource(R.drawable.bg_completed_paid);
            holder.tickImage_replace.setBackgroundResource(R.drawable.tick_icon);
            holder.pdfId.setBackgroundResource(R.drawable.pdf_green);
            holder.amounts.setTextColor(ContextCompat.getColor(mCtx,R.color.pdf_green));
        }

        holder.menu_bar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openView(position);
            }
        });
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(v,position);
            }
        });

    }
    private void openFragment(View v, int position) {
        JobListModel clickedItem = jobListModelList.get(position);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(clickedItem).getAsJsonObject();
        System.out.println("clickedItem"+" "+jsonObject);
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        Fragment fragment = new JobListDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("jsonObject", jsonObject.toString());
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
        activity.getSupportActionBar().setTitle("Job Details");
    }
    private void openView(int position) {
        sheetDialog = new BottomSheetDialog(mCtx, R.style.BottomSheetStyle);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View jobOptions = inflater.inflate(R.layout.job_list_action, null);

        pay = jobOptions.findViewById(R.id.pay);
        pdf = jobOptions.findViewById(R.id.pdf);
        delete = jobOptions.findViewById(R.id.delete);
        cancel = jobOptions.findViewById(R.id.cancel);

        if(jobListModelList.get(position).getJobStatus().equals("Quote")) {
            pay.setVisibility(View.VISIBLE);
        } else {
            pay.setVisibility(View.GONE);
        }

        pdf.setOnClickListener(v -> {
            Intent pdfView = new Intent(mCtx, PdfView.class);
            pdfView.putExtra("invoiceId", jobListModelList.get(position).getInvNumber());
            pdfView.putExtra("pdfLink", jobListModelList.get(position).getPdfLink());
            mCtx.startActivity(pdfView);
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PaymentOptionActivity.class);
                intent.putExtra("amount", jobListModelList.get(position).getAmount());
                mCtx.startActivity(intent);
            }
        });
        cancel.setOnClickListener(v -> {
            sheetDialog.dismiss();
        });
        sheetDialog.setContentView(jobOptions);
        sheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return jobListModelList.size();
    }
    class JobListViewHolder extends RecyclerView.ViewHolder {
        TextView countryName,companyName,manuBar,quotedId_text,translateFrom_text,translateTo_text,amounts,completed_paidID, jobName;
        ImageView pdfId,cardId,tickImage_replace;
        RelativeLayout completed_paid_rv,rv;
        LinearLayout menu_bar_layout;
        public JobListViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            companyName = itemView.findViewById(R.id.companyName);
            manuBar = itemView.findViewById(R.id.manuBar);
            quotedId_text = itemView.findViewById(R.id.quotedId_text);
            translateFrom_text = itemView.findViewById(R.id.translateFrom_text);
            translateTo_text = itemView.findViewById(R.id.translateTo_text);
            amounts = itemView.findViewById(R.id.amunts);
            completed_paidID = itemView.findViewById(R.id.completed_paidID);
            pdfId = itemView.findViewById(R.id.pdfId);
            cardId = itemView.findViewById(R.id.cardId);
            tickImage_replace = itemView.findViewById(R.id.tickImage_replace);
            completed_paid_rv = itemView.findViewById(R.id.completed_paid_rv);
            jobName = itemView.findViewById(R.id.jobName);
            menu_bar_layout = itemView.findViewById(R.id.menu_bar_layout);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}
