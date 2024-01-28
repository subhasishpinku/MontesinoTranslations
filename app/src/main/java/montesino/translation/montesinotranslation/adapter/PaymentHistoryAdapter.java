package montesino.translation.montesinotranslation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.model.PaymentHistoryModel;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHistoryViewHolder> {
    private Context mCtx;
    private List<PaymentHistoryModel> paymentHistoryModels;
    public PaymentHistoryAdapter(Context mCtx, List<PaymentHistoryModel> paymentHistoryModels) {
        this.mCtx = mCtx;
        this.paymentHistoryModels = paymentHistoryModels;
    }
    @Override
    public PaymentHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View snapImage = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_list_adapter, parent, false);
        return new PaymentHistoryViewHolder(snapImage);
    }
    @Override
    public void onBindViewHolder(PaymentHistoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //getting the product of the specified position
        PaymentHistoryModel paymentHistoryModel = paymentHistoryModels.get(position);
        holder.transactionNumberId.setText(paymentHistoryModel.getItemId());
        holder.timeId.setText(paymentHistoryModel.getCreatedTime());
        System.out.println("TxnId"+" "+paymentHistoryModel.getTxnId());
        if (paymentHistoryModel.getTxnId() != null) {
            holder.transactionId.setText(paymentHistoryModel.getTxnId());
        } else {
            holder.transactionId.setVisibility(View.GONE);
        }
        holder.emailID.setText(paymentHistoryModel.getEmail());
        holder.quotedAmountId.setText(paymentHistoryModel.getQuotedAmount());
        holder.paidAmountID.setText(paymentHistoryModel.getPaymentAmount());
        holder.quotedAmountId2.setText(paymentHistoryModel.getQuotedAmount2());
        holder.transactionDollarUsedID.setText(paymentHistoryModel.getDollarUsed());
        if (paymentHistoryModel.getPaymentStatus().equals("Approved")){
            holder.tickImage_replace.setBackgroundResource(R.drawable.tick_icon_history);
            holder.text_status.setText(mCtx.getResources().getString(R.string.approved));
            holder.text_status.setTextColor(ContextCompat.getColor(mCtx, R.color.county_Name));
        }else if (paymentHistoryModel.getPaymentStatus().equals("Reject")){
            holder.tickImage_replace.setBackgroundResource(R.drawable.reject_icon_history);
            holder.text_status.setText(mCtx.getResources().getString(R.string.reject));
            holder.text_status.setTextColor(ContextCompat.getColor(mCtx, R.color.amount_color));
        }else if (paymentHistoryModel.getPaymentStatus().equals("Pending")){
            holder.tickImage_replace.setBackgroundResource(R.drawable.pending_icon_history);
            holder.text_status.setText(mCtx.getResources().getString(R.string.pending));
            holder.text_status.setTextColor(ContextCompat.getColor(mCtx, R.color.orange));
        }
    }
    @Override
    public int getItemCount() {
        return paymentHistoryModels.size();
    }
    class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {
       TextView transactionNumberId,dateId,timeId,transactionId,emailID,quotedAmountId,paidAmountID,quotedAmountId2,transactionDollarUsedID,text_status;
       ImageView tickImage_replace;

        public PaymentHistoryViewHolder(View itemView) {
            super(itemView);
            transactionNumberId = itemView.findViewById(R.id.transactionNumberId);
            dateId = itemView.findViewById(R.id.dateId);
            timeId = itemView.findViewById(R.id.timeId);
            transactionId = itemView.findViewById(R.id.transactionId);
            emailID = itemView.findViewById(R.id.emailID);
            quotedAmountId = itemView.findViewById(R.id.quotedAmountId);
            paidAmountID = itemView.findViewById(R.id.paidAmountID);
            quotedAmountId2 = itemView.findViewById(R.id.quotedAmountId2);
            transactionDollarUsedID = itemView.findViewById(R.id.transactionDollarUsedID);
            tickImage_replace = itemView.findViewById(R.id.tickImage_replace);
            text_status = itemView.findViewById(R.id.text_status);
        }
    }
}