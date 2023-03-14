package org.overlake.mat803.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {

    List<String> mWords;

    WordListAdapter(List<String> words) {
        mWords = words;
        not
    }

    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WordListViewHolder extends RecyclerView.ViewHolder {
        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
