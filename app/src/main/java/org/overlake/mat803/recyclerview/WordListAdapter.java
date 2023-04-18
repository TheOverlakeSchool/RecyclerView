package org.overlake.mat803.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import org.overlake.mat803.recyclerview.database.Word;
import org.overlake.mat803.recyclerview.databinding.WordListItemBinding;

import java.util.List;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {

    List<Word> mWords;
    LayoutInflater mLayoutInflater;
    NavController mNavController;
    Fragment mHost;

    WordListAdapter(List<Word> wordList, Fragment host) {
        mWords = wordList;
        mHost = host;
        mLayoutInflater = host.getLayoutInflater();
        mNavController = NavHostFragment.findNavController(host);
    }

    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordListItemBinding binding = WordListItemBinding.inflate(mLayoutInflater);
        return new WordListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {
        holder.setText(mWords.get(position).getWord());
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
                Bundle bundle = new Bundle();
                bundle.putInt(WordListUpdateFragment.POSITION, getLayoutPosition());
                bundle.putString(WordListUpdateFragment.WORD, mWords.get(getLayoutPosition()).getWord());

                mNavController.navigate(R.id.wordListUpdateFragment, bundle);
            });
        }

        public void setText(String word) {
            mWord.setText(word);
        }
    }

}
