package org.overlake.mat803.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class WordList {

   private List<String> mWords;
   public static WordList sWordList = new WordList();

   private WordList() {
      mWords = new ArrayList<>(20);
      for (int i = 0; i < 20; i++) {
         mWords.add("Word" + i);
      }
   }

   public static WordList getInstance() {
       return sWordList;
   }


   public List<String> getWords() {
      return mWords;
   }

}
