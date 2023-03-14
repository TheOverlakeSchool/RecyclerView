package org.overlake.mat803.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import org.overlake.mat803.recyclerview.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        List<String> words = WordList.getInstance().getWords();
        binding.recyclerView.setAdapter(new WordListAdapter(words));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.activity_main);
    }
}