package itacademy.snowadv.javaedu.ui.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.data.UserData;

public class QuickTestDialogFragment extends DialogFragment {
    private UserData userData;
    private RadioGroup radioGroup;
    private ProgressFragment fragment;
    public QuickTestDialogFragment() {
        // Needed to implement DialogFragment
    }

    public QuickTestDialogFragment(UserData userData, ProgressFragment fragment) {
        this.userData = userData;
        this.fragment = fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quick_task_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QuickTask task = userData.getRandomQuickTask();

        radioGroup = view.findViewById(R.id.radio_answer);
        Collections.shuffle(task.getChoices());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(task.getChoices().get(i));
        }
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(((RadioButton)view.findViewById(checkedId)).getText().toString()
                .equals(task.getRightAnswer())) {
                Toast.makeText(getActivity(), "Верный ответ! :)", Toast.LENGTH_LONG).show();
                userData.increaseDailyTasksDoneCounter();
                userData.finishQuickTask(task);
                fragment.updateByUserData();
            } else {
                Toast.makeText(getActivity(), "Ответ неверный :( Пробуйте еще!"
                        , Toast.LENGTH_LONG).show();
            }
            getDialog().dismiss();
        });
        ((TextView)view.findViewById(R.id.task_text)).setText(task.getText());
    }



}
