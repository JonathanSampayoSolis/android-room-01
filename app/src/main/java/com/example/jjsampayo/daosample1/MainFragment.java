package com.example.jjsampayo.daosample1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jjsampayo.daosample1.adapters.FriendMainAdapter;
import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;
import com.example.jjsampayo.daosample1.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnLongClickListener {

    private View mainView;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MainViewModel viewModelFriend;

    private FriendMainAdapter adapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
                            , @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = mainView.findViewById(R.id.fra_main_recycler);
        progressBar = mainView.findViewById(R.id.fra_main_progress);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FriendMainAdapter(new ArrayList<Friend>(), this);
        recyclerView.setAdapter(adapter);

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            viewModelFriend = ViewModelProviders.of(this).get(MainViewModel.class);

            viewModelFriend.getFriendList().observe(getActivity(), new Observer<List<Friend>>() {
                @Override
                public void onChanged(@Nullable List<Friend> friends) {
                    adapter.addList(friends);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public boolean onLongClick(View v) {
        viewModelFriend.deleteFriend((Friend) v.getTag());
        return true;
    }
}
