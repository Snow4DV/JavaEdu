package itacademy.snowadv.javaedu.ui.theory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import itacademy.snowadv.javaedu.MainActivity;
import itacademy.snowadv.javaedu.data.UserData;
import itacademy.snowadv.javaedu.databinding.FragmentTheoryBinding;

public class TheoryFragment extends Fragment {

    private FragmentTheoryBinding binding;
    private UserData userData;
    private TheoryAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTheoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userData = getUserDataFromMainActivity();
        adapter = new TheoryAdapter(userData, getActivity());
        binding.theoryRecyclerView.setAdapter(adapter);
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