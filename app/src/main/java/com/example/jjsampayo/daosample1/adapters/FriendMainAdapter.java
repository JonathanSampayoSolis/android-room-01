package com.example.jjsampayo.daosample1.adapters;

import android.databinding.DataBindingUtil;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jjsampayo.daosample1.databinding.ItemFragmentMainBinding;

import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;

import java.util.List;

public class FriendMainAdapter extends RecyclerView.Adapter<FriendMainAdapter.ViewHolder> {
    private List<Friend> friends;
    private View.OnLongClickListener longClickListener;

    public FriendMainAdapter(List<Friend> friends, View.OnLongClickListener longClickListener) {
        this.friends = friends;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFragmentMainBinding binding = ItemFragmentMainBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(friends.get(position));
        holder.binding.setFriendModel(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void addList(List<Friend> list) {
        this.friends = list;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFragmentMainBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

            itemView.setOnLongClickListener(longClickListener);
        }
    }

}
