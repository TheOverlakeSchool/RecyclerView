package org.overlake.mat803.recyclerview.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao getDao();
}
