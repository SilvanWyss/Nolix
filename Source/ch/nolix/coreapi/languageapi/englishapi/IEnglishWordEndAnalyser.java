package ch.nolix.coreapi.languageapi.englishapi;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IEnglishWordEndAnalyser {

  /**
   * @param word
   * @return true if the given word ends with a vocal and y, false otherwise.
   */
  boolean endsWithVocalAndY(final String word);
}
