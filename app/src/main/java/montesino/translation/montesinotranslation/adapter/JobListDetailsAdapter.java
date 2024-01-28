package montesino.translation.montesinotranslation.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.model.JobListDetailsModel;
public class JobListDetailsAdapter extends RecyclerView.Adapter<JobListDetailsAdapter.JobListDetailsViewHolder> {
    private Context mCtx;
    private List<JobListDetailsModel> jobListDetailsModelList;
    public JobListDetailsAdapter(Context mCtx, List<JobListDetailsModel> jobListDetailsModelList) {
        this.mCtx = mCtx;
        this.jobListDetailsModelList = jobListDetailsModelList;
    }
    @Override
    public JobListDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View snapImage = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_job_statues, parent, false);
        return new JobListDetailsViewHolder(snapImage);
    }
    @Override
    public void onBindViewHolder(JobListDetailsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //getting the product of the specified position
        JobListDetailsModel jobListDetailsModel = jobListDetailsModelList.get(position);
        holder.track_job_listTitle.setText(jobListDetailsModel.getTitleJobDetails());
        holder.track_job_listSubTitle.setText(jobListDetailsModel.getSubTitleJobDetails());
        if (jobListDetailsModel.getTitleJobDetails().equals("Notarized Conv Sent?")){
            holder.arrow_line.setVisibility(View.GONE);
        }else {
            holder.arrow_line.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return jobListDetailsModelList.size();
    }
    class JobListDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView track_job_listTitle,track_job_listSubTitle;
        ImageView image_money;
        View arrow_line;
        public JobListDetailsViewHolder(View itemView) {
            super(itemView);
            track_job_listTitle = itemView.findViewById(R.id.track_job_listTitle);
            track_job_listSubTitle = itemView.findViewById(R.id.track_job_listSubTitle);
            image_money = itemView.findViewById(R.id.image_money);
            arrow_line = itemView.findViewById(R.id.arrow_line);
        }
    }
}