package org.overlake.mat803.recyclerview.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM words")
    List<Word> getWords();
}
