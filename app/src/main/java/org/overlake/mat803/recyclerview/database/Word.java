package org.overlake.mat803.recyclerview.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {

   @PrimaryKey
   private int id;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getWord() {
      return word;
   }

   public void setWord(String word) {
      this.word = word;
   }

   public String getDefinition() {
      return definition;
   }

   public void setDefinition(String definition) {
      this.definition = definition;
   }

   private String word;
   private String definition;
}
