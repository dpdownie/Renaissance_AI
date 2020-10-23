/**
 * Author: Drew Downie (downie@cs.wisc.edu)
 * Last Updated: 10/23/2000
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * LanguageModel:
 * TODO Description
 * 
 * @author Drew Downie (2020)
 *
 */
public class LanguageModel {

  private HashMap<String, Word> alphabet = new HashMap<String, Word>();

  // private HashMap<String, Integer> unigrams = new HashMap<String, Integer>();
  // private HashMap<String, Integer> bigrams = new HashMap<String, Integer>();
  // private HashMap<String, Integer> trigrams = new HashMap<String, Integer>();
  // private HashMap<String, Double> unigramProb = new HashMap<String, Double>(); // P(x)
  // private HashMap<String, Double> bigramProb = new HashMap<String, Double>(); // P(y|x)
  // private HashMap<String, Double> trigramProb = new HashMap<String, Double>(); // P(z|xy)

  public LanguageModel() {
    try {
      this.updateAlphabet("texts/common_words.txt");
    } catch (FileNotFoundException e) {
      System.out.println("File not found, alphabet not initialized.");
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IOException encountered.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  public LanguageModel(String filename) {
    try {
      this.updateAlphabet(filename);
    } catch (FileNotFoundException e) {
      System.out.println("File not found, alphabet not initialized.");
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IOException encountered.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  public HashMap<String, Word> getAlphabet() {
    return this.alphabet;
  }

  /**
   * @param filename
   */
  public void readText(String filename) {
    try {
      this.updateAlphabet(filename);
    } catch (FileNotFoundException e) {
      System.out.println("File not found, unable to update alphabet.");
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IOException encountered.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * @param input
   * @throws FileNotFoundException
   * @throws IOException
   */
  private void updateAlphabet(String input) throws FileNotFoundException, IOException {
    File inputFile = new File(input);
    BufferedReader br = new BufferedReader(new FileReader(inputFile));
    String text = br.readLine();
    Scanner textReader = new Scanner(text);
    textReader.useDelimiter(" ");

    while (textReader.hasNext()) {
      String currWord = textReader.next().trim();
      Word newWord = new Word(currWord);
      if (!alphabet.containsKey(currWord)) {
        alphabet.put(currWord, newWord);
      }
    }

    textReader.close();
    br.close();
  }

  public static void main(String[] args) {
    LanguageModel lm = new LanguageModel();
    System.out.println(lm.alphabet.toString());
  }

}
