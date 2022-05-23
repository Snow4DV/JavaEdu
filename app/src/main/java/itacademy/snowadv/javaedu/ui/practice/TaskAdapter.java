package itacademy.snowadv.javaedu.ui.practice;

import android.content.Context;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.noties.markwon.Markwon;
import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.data.TaskData;
import itacademy.snowadv.javaedu.data.UserData;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private UserData userData;
    private Context context;
    private TaskCallback taskCallback;

    public TaskAdapter(UserData userData, Context context, TaskCallback taskCallback) {
        this.userData = userData;
        this.context = context;
        this.taskCallback = taskCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return UserData.TASKS_AMOUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox isDoneCheckbox;

        public void bind(int position) {
            isDoneCheckbox.setChecked(userData.getTaskStatus(position));
            taskText.setText(TaskData.getTaskByID(position).getName());
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.task_name);
            isDoneCheckbox = itemView.findViewById(R.id.is_task_done_checkbox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskCallback.execute(TaskData.getTaskByID(getAdapterPosition()));
                }
            });
        }


    }
}
