package org.overlake.mat803.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.overlake.mat803.recyclerview.databinding.WordListItemBinding;

import java.util.List;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {

    List<String> mWords;
    LayoutInflater mLayoutInflater;
    int holderCount;

    WordListAdapter(List<String> words, LayoutInflater inflater) {
        mWords = words;
        mLayoutInflater = inflater;
    }

    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordListItemBinding binding = WordListItemBinding.inflate(mLayoutInflater);
        Log.d("WordListAdapter", "Holder created: " + ++holderCount);
        return new WordListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {
        holder.setText(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    class WordListViewHolder extends RecyclerView.ViewHolder {

        private TextView mWord;

        public WordListViewHolder(WordListItemBinding binding) {
            super(binding.getRoot());
            mWord = binding.word;
            mWord.setOnClickListener(v-> {
                mWord.setText(mWord.getText() + " -- updated");
            });
        }

        public void setText(String word) {
            mWord.setText(word);
        }
    }

}
