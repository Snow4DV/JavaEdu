package itacademy.snowadv.javaedu.ui.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import itacademy.snowadv.javaedu.MainActivity;
import itacademy.snowadv.javaedu.data.TaskData;
import itacademy.snowadv.javaedu.data.UserData;
import itacademy.snowadv.javaedu.databinding.FragmentPracticeBinding;
import itacademy.snowadv.javaedu.ui.task.TaskActivity;

public class PracticeFragment extends Fragment {

    private FragmentPracticeBinding binding;
    private TaskAdapter taskAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPracticeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        taskAdapter = new TaskAdapter(getUserDataFromMainActivity(), getActivity(), task -> {
            Intent intent = new Intent(getActivity(), TaskActivity.class);
            intent.putExtra("taskID", task.getId());
            getActivity().startActivity(intent);
        });
        binding.tasksRecyclerView.setAdapter(taskAdapter);
        return root;
    }



    private UserData getUserDataFromMainActivity() {
        if(getActivity() instanceof MainActivity) {
            return ((MainActivity)getActivity()).getUserDataInstance();
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}