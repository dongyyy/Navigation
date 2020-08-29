package com.dongy.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    List<String> mList;

    public ExampleAdapter(List<String> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ExampleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.nameTextView)).setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
