package first.app.e_gouvernance.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import first.app.e_gouvernance.R;
import first.app.e_gouvernance.model.Soumission;
import first.app.e_gouvernance.view.SoumissionDetails;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Soumission> dataList;

    public TableAdapter(List<Soumission> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_table_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Soumission data = dataList.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  textViewTitle, textViewSubmissionDate, textViewStatus;
        private Button actionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSubmissionDate = itemView.findViewById(R.id.textViewSubmissionDate);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            actionButton = itemView.findViewById(R.id.actionButton);
        }

        public void bindData(Soumission data) {
            textViewTitle.setText(data.getTender().getTitle());
            textViewSubmissionDate.setText(data.getDateSoumission());
            String statusAsString = "none";
            if (data.getStatus() == 0) {
                statusAsString = "Rejeté";
            } else if (data.getStatus() == 1) {
                statusAsString = "Validé";
            }
            textViewStatus.setText(statusAsString);
            // Bind other views
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Soumission data = dataList.get(getAdapterPosition());

                    Intent intent = new Intent(context, SoumissionDetails.class);
                    intent.putExtra("id", data.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
