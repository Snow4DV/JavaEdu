package itacademy.snowadv.javaedu.ui.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import itacademy.snowadv.javaedu.MainActivity;
import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.data.UserData;
import itacademy.snowadv.javaedu.databinding.FragmentProgressBinding;

public class ProgressFragment extends Fragment {

    private FragmentProgressBinding binding;
    private UserData userData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userData = getUserDataFromMainActivity();
        updateByUserData();
        binding.dailyGoalSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userData.setDailyGoal(progress);
                updateByUserData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        binding.doQuickTaskButton.setOnClickListener(v -> {
            QuickTestDialogFragment quickTestDialogFragment = new QuickTestDialogFragment(userData,
                    this);
            quickTestDialogFragment.show(getParentFragmentManager(), "Quick Task");
        });
        return root;
    }


    protected void updateByUserData() {
        updateRings();
        setDashboardText();
        updateSeekbar();
    }

    private void updateSeekbar() {
        binding.dailyGoalSeekbar.setProgress(userData.getDailyGoal());
        updateSeekbarText();
    }

    private void updateSeekbarText() {
        String difficulty;
        if(userData.getDailyGoal() >= 7) {
            difficulty = getString(R.string.difficulty_high);
        } else if(userData.getDailyGoal() >= 4) {
            difficulty = getString(R.string.difficulty_mid);
        } else {
            difficulty = getString(R.string.difficulty_low);
        }
        binding.difficultyOfDailyGoal.setText(getString(R.string.seekbar_difficulty_text,
                difficulty));
        binding.dailyGoal.setText(getString(R.string.seekbar_amount_text,
                userData.getDailyGoal()));
    }

    private void updateRings() {
        binding.dailyProgressIndicator.setProgress(userData.getDailyGoalPercent());
        binding.tasksDoneProgressIndicator.setProgress(userData.getTasksPercent());
        binding.theoryReadProgressIndicator.setProgress(userData.getTheoryPercent());

        binding.dailyProgressIndicatorText.setText(userData.getDailyGoalPercent() + "%");
        binding.theoryReadProgressIndicatorText.setText(userData.getTheoryReadAmount() + "/"
        + UserData.THEORY_AMOUNT);
        binding.tasksDoneProgressIndicatorText.setText(userData.getTasksDoneAmount() + "/"
                + UserData.TASKS_AMOUNT);
    }


    private void setDashboardText() {
        int doneTasksPercent = userData.getDailyGoalPercent();
        if(doneTasksPercent >= 100) {
            binding.cheerText.setText(R.string.cheer_100);
        } else if(doneTasksPercent >= 70) {
            binding.cheerText.setText(R.string.cheer_70);
        } else if(doneTasksPercent >= 30) {
            binding.cheerText.setText(R.string.cheer_30);
        } else {
            binding.cheerText.setText(R.string.cheer_0);
        }
    }

    private UserData getUserDataFromMainActivity() {
        if(getActivity() instanceof MainActivity) {
            return ((MainActivity)getActivity()).getUserDataInstance();
        }
        return null;
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            updateByUserData();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        updateByUserData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}