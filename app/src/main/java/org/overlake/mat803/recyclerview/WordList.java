package org.overlake.mat803.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class WordList {

   private List<String> mWords;
   public static WordList sWordList = new WordList();

   private WordList() {
      reset();
   }

   public static WordList getInstance() {
       return sWordList;
   }


   public List<String> getWords() {
      return mWords;
   }

   public void set(String word, int position) {
      mWords.set(position, word);
   }

   public String get(int position) {
      return mWords.get(position);
   }

   public void remove(int position) {
      mWords.remove(position);
   }

   public void reset() {
      mWords = new ArrayList<>(20);
      for (int i = 1; i <= 20; i++) {
         mWords.add("Word" + i);
      }
   }
}
