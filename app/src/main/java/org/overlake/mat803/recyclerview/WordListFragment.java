package org.overlake.mat803.recyclerview;

import static org.overlake.mat803.recyclerview.WordListUpdateFragment.ACTION;
import static org.overlake.mat803.recyclerview.WordListUpdateFragment.DIALOG_RESULT;
import static org.overlake.mat803.recyclerview.WordListUpdateFragment.POSITION;
import static org.overlake.mat803.recyclerview.WordListUpdateFragment.WORD;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.overlake.mat803.recyclerview.databinding.WordListFragmentBinding;

import java.util.List;

public class WordListFragment extends Fragment {
   private WordList mWords;


   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      WordListFragmentBinding binding = WordListFragmentBinding.inflate(getLayoutInflater());
      mWords = WordList.getInstance();
      binding.recyclerView.setAdapter(new WordListAdapter(mWords, this));
      binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      getParentFragmentManager().setFragmentResultListener(DIALOG_RESULT, this, new FragmentResultListener() {
         @Override
         public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
            int position = result.getInt(POSITION);
            if (result.getInt(ACTION) == AlertDialog.BUTTON_NEGATIVE) {
               mWords.remove(position);
               binding.recyclerView.getAdapter().notifyDataSetChanged();
            } else {
               String word = result.getString(WORD);
               mWords.set(word, position);
               binding.recyclerView.getAdapter().notifyItemChanged(position);
            }

         }
      });

      getActivity().addMenuProvider(new MenuProvider() {
         @Override
         public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.options, menu);
         }

         @Override
         public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            mWords.reset();
            binding.recyclerView.getAdapter().notifyDataSetChanged();
            return true;
         }
      });
      return binding.getRoot();
   }


}
