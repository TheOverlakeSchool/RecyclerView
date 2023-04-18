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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import org.overlake.mat803.recyclerview.database.Word;
import org.overlake.mat803.recyclerview.database.WordDao;
import org.overlake.mat803.recyclerview.database.WordDatabase;
import org.overlake.mat803.recyclerview.databinding.WordListFragmentBinding;

import java.util.List;

public class WordListFragment extends Fragment {

   private List<Word> mWords;
   private org.overlake.mat803.recyclerview.databinding.WordListFragmentBinding mBinding;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      mBinding = WordListFragmentBinding.inflate(getLayoutInflater());
      WordDatabase database = Room.databaseBuilder(getContext(), WordDatabase.class, "WordDatabase.db")
                      .createFromAsset("database/words.db")
                              .allowMainThreadQueries()
                                      .build();
      WordDao dao = database.getDao();
      mWords = dao.getWords();
      mBinding.recyclerView.setAdapter(new WordListAdapter(mWords, this));
      mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      setListener();
      setMenu();
   }

   private void setListener() {
      getParentFragmentManager().setFragmentResultListener(DIALOG_RESULT, this, new FragmentResultListener() {
         @Override
         public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
            int position = result.getInt(POSITION);
            if (result.getInt(ACTION) == AlertDialog.BUTTON_NEGATIVE) {
          //     mWords.remove(position);
               mBinding.recyclerView.getAdapter().notifyDataSetChanged();
            } else {
               String word = result.getString(WORD);
          //     mWords.set(word, position);
               mBinding.recyclerView.getAdapter().notifyItemChanged(position);
            }
         }
      });
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return mBinding.getRoot();
   }

   private void setMenu() {
      getActivity().addMenuProvider(new MenuProvider() {
         @Override
         public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.options, menu);
         }

         @Override
         public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
           // mWords.reset();
            mBinding.recyclerView.getAdapter().notifyDataSetChanged();
            return true;
         }
      });
   }


}
