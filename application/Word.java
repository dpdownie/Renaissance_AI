/**
 * Author: Drew Downie (dpdownie@wisc.edu)
 * Last Updated: 10/23/2000
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
  
  public static final int ASCII_Z = 0x5A; // Index of ASCII char capital 'Z'

  private static final ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a',
      'e', 'i', 'o', 'u'));
  private String word;
  private int length;
  private int syllables;
  private boolean properNoun;

  public Word() {
    word = "";
    length = 0;
    syllables = 0;
    properNoun = false;
  }

  public Word(String word) {
    this.word = word.toLowerCase().trim();
    this.length = word.length();
    this.syllables = calcSyllables();
    if (word.charAt(0) <= ASCII_Z)
      properNoun = true;
  }

  public int getLength() {
    return length;
  }

  public int getSyllables() {
    return syllables;
  }

  public boolean isProperNoun() {
    return properNoun;
  }

  /**
   * containsVowel():
   * Private method that checks if a String contains a vowel.
   * 
   * @param in - input String
   * @return true if input contains a vowel (aeiouy), false otherwise
   */
  private boolean containsVowel(String in) {
    for (char c : in.toCharArray()) {
      if (vowels.contains(c)) {
        return true;
      }
    }

    return false;
  }

  /**
   * getSyllables():
   * Public method that returns the number of syllables in a given word.
   * This method is not entirely comprehensive due to the irregularities of the English language.
   * It is designed to recognize commonly occurring patterns, and should therefore classify many
   * words correctly, but not all.
   * 
   * @param word - English word for which to count syllables
   * @return number of syllables in given word
   */
  public int calcSyllables() {
    if (word == null) {
      return 0;
    }

    String[] strSplit = word.split("(?<=a)|(?<=e)|(?<=i)|(?<=o)|(?<=u)|(?<=y)");
    LinkedList<String> sylList = new LinkedList<String>();
    for (int i = 0; i < strSplit.length; i++) {
      sylList.add(strSplit[i]);
    }

    /* Coalesce dipthongs, tripthongs, and double vowels */
    for (int i = 0; i < sylList.size(); i++) {
      if (sylList.get(i).length() == 1 && i != 0) {
        String vowel = sylList.get(i);
        String prevSyl = sylList.get(i - 1);
        if (!(vowel.equals("a") && prevSyl.endsWith("i") && !word.endsWith("ia"))) {
          sylList.set(i - 1, prevSyl.concat(vowel));
          sylList.remove(vowel);
          i--;
        }
      }
    }

    /* Remove silent 'e' unless word ends with "le" or "les" preceeded by a consonant */
    String lastSyl = sylList.getLast();
    if (sylList.size() >= 2 && (lastSyl.endsWith("e") || lastSyl.endsWith("es"))) {
      if (!(lastSyl.endsWith("le") && !vowels.contains(word.charAt(length - 3)))
          && !lastSyl.endsWith("les")) {
        String silentE = lastSyl;
        String prevSyl = sylList.get(sylList.size() - 2);
        sylList.set(sylList.size() - 2, prevSyl.concat(silentE));
        sylList.remove(silentE);
      } else if (!(lastSyl.endsWith("les") && !vowels.contains(word.charAt(length - 4)))) {
        String silentE = lastSyl;
        String prevSyl = sylList.get(sylList.size() - 2);
        sylList.set(sylList.size() - 2, prevSyl.concat(silentE));
        sylList.remove(silentE);
      }
    }

    /**
     * If the last syllable contains only consonants, append to previous syllable
     * Exception: "ing"
     */
    lastSyl = sylList.getLast();
    if (sylList.size() >= 2 && !containsVowel(lastSyl)) {
      if (!(word.endsWith("ing") && containsVowel(String.valueOf(word.charAt(length - 4))))) {
        String prevSyl = sylList.get(sylList.size() - 2);
        String consonants = lastSyl;
        sylList.set(sylList.size() - 2, prevSyl.concat(consonants));
        sylList.removeLast();
      }
    }

    /**
     * Treat prefixes "co" and "re", succeeded by a vowel, as their own syllables
     * Exception: single-syllable words, i.e. "coin"
     */
    String firstSyl = sylList.getFirst();
    if (((firstSyl.startsWith("co") || firstSyl.startsWith("re")) && firstSyl.length() > 2)
        && vowels.contains(firstSyl.charAt(2)) && sylList.size() != 1) {
      String prefix = firstSyl.substring(0, 2);
      String rest = firstSyl.substring(2, firstSyl.length());
      sylList.add(0, prefix);
      sylList.set(1, rest);
    }

    return sylList.size();
  }
  
  @Override
  public String toString() {
    return word;
  }

}
