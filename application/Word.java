/**
 * Author: Drew Downie (dpdownie@wisc.edu) Date: 10/22/2000
 */
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Word:
 * Class that represents a single word in the English language.
 * 
 * @author Drew Downie (2020)
 *
 */
public class Word {

  private ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o',
      'u'));
  private String word;
  private int length;
  private int syllables;

  public Word() {
    word = "";
    length = 0;
    syllables = 0;
  }

  public Word(String word) {
    this.word = word.toLowerCase().trim();
    this.length = word.length();
    this.syllables = getSyllables();
  }

  /**
   * getSyllables():
   * Public method that returns the number of syllables in a given word.
   * This method is not entirely comprehensive due to the irregularities of the English language.
   * It is designed to recognize commonly occurring patterns, and should classify many words
   * correctly.
   * 
   * @param word - English word for which to count syllables
   * @return number of syllables in given word
   */
  public int getSyllables() {
    if (word == null) {
      return 0;
    }

    int syllables = 0;
    
    // SPLIT AND LINKED LIST METHOD
    String[] strSplit = word.split("(?<=a)|(?<=e)|(?<=i)|(?<=o)|(?<=u)|(?<=y)");
    LinkedList<String> sylList = new LinkedList<String>();
    for (int i = 0; i < strSplit.length; i++) {
      sylList.add(strSplit[i]);
    }
    
    System.out.println(sylList.toString());
    
    
    for (int i = 0; i < length; i++) { // Iteratively check for vowels

      // Word must be formatted to be all lowercase
      if (vowels.contains(word.charAt(i))) {
        syllables++;

        if (i == length - 1 && word.charAt(i) == 'e') { // Discount silent 'e'
          syllables--;
        } else if (i != 0 && vowels.contains(word.charAt(i - 1))) { // Discount dipthongs &
                                                                    // tripthongs
          syllables--;
        } else if (i != 0 && (word.charAt(i - 1) == word.charAt(i))) { // Discount double vowels
                                                                       // 'ee'/'oo'
          syllables--;
        }
      }
    }

    /* IRREGULARITIES */

    // If the word begins with a prefix, i.e. "co", "re",
    // count the prefix as a syllable only if the succeeding letter is a vowel
    if ((word.startsWith("co") || word.startsWith("re")) && vowels.contains(word.charAt(2))) {
      syllables++;
    }

    // Add a syllable if the word ends with 'le' and the letter before is a consonant
    if (word.endsWith("le") && !vowels.contains(word.charAt(length - 3))) {
      syllables++;
    }

    /**
     * Count 'y' as a syllable only on conditions:
     * 1. The word has no other vowel
     * 2. 'y' is at the end of a word or syllable
     * 3. 'y' is in the middle of a syllable
     */
    if (syllables == 0 && word.contains("y")) {
      syllables++;
    } else if (word.endsWith("y")) {
      syllables++;
      if (vowels.contains(word.charAt(length - 2))) {
        syllables--;
      }
    }

    return syllables;
  }

  public static void main(String[] args) {
    Word apple = new Word("rendesvous");
    System.out.print(apple.syllables);
  }


}
