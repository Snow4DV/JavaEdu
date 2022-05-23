package itacademy.snowadv.javaedu.ui.theory;

import android.content.Context;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.noties.markwon.Markwon;
import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.data.UserData;

public class TheoryAdapter extends RecyclerView.Adapter<TheoryAdapter.ViewHolder> {

    private UserData userData;
    private Context context;

    public TheoryAdapter(UserData userData, Context context) {
        this.userData = userData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.theory_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return UserData.THEORY_AMOUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView theoryText;
        CheckBox isReadCheckbox;

        public void bind(int position) {
            final Markwon markwon = Markwon.create(context);
            final Spanned markdown = markwon.toMarkdown(context.getString(UserData
                    .getTheoryStringResByID(position)));
            markwon.setParsedMarkdown(theoryText, markdown);
            isReadCheckbox.setChecked(userData.getTheoryStatus(position));
            isReadCheckbox.setOnClickListener(v -> {
                userData.setTheoryStatus(position, isReadCheckbox.isChecked());
            });
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theoryText = itemView.findViewById(R.id.theory_text);
            isReadCheckbox = itemView.findViewById(R.id.theory_read_checkbox);
        }


    }
}
