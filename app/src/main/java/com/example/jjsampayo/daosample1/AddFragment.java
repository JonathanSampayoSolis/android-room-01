package com.example.jjsampayo.daosample1;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jjsampayo.daosample1.repositories.local.AppDatabase;
import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;
import com.example.jjsampayo.daosample1.viewmodel.AddViewModel;

public class AddFragment extends Fragment {

    private View mainView;
    private EditText editTextName, editTextPhone;
    private Button button;

    private AddViewModel viewModel;

    public static AddFragment newInstance() {

        Bundle args = new Bundle();

        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_add, container, false);

        editTextName  = mainView.findViewById(R.id.fra_add_name);
        editTextPhone = mainView.findViewById(R.id.fra_add_phone);
        button        = mainView.findViewById(R.id.fra_add_btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend(editTextName.getText().toString()
                                , editTextPhone.getText().toString());

                viewModel.addFriend(friend);

                openMainFragment();
            }
        });

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AddViewModel.class);
    }

    private void openMainFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_main_content, MainFragment.newInstance())
                .commit();
    }

}
