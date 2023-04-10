package org.overlake.mat803.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import org.overlake.mat803.recyclerview.databinding.WordDialogBinding;

public class WordListUpdateFragment extends DialogFragment {

    public static final String POSITION = "position";
    public static final String WORD = "word";
    public static final String DIALOG_RESULT = "dialog_result";
    public static final String ACTION = "action";
    private WordList mWordList;
    FragmentManager mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWordList = WordList.getInstance();
        mFragmentManager = this.getParentFragmentManager();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        WordDialogBinding binding = WordDialogBinding.inflate(getLayoutInflater());
        Bundle arguments = getArguments();
        int position = arguments.getInt(POSITION);
        String word = mWordList.get(position);
        binding.input.setText(word);
        return new AlertDialog.Builder(getContext())
                .setMessage("Word #" + (position + 1) + ":")
                .setView(binding.getRoot())
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(POSITION, position);
                        bundle.putString(WORD, binding.input.getText().toString());
                        mFragmentManager.setFragmentResult(DIALOG_RESULT, bundle);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(POSITION, position);
                        bundle.putInt(ACTION, which);
                        mFragmentManager.setFragmentResult(DIALOG_RESULT, bundle);
                    }
                })
                .setNeutralButton("Cancel", null)
                .create();
    }
}
