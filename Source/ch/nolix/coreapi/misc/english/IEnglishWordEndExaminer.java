package ch.nolix.coreapi.misc.english;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IEnglishWordEndExaminer {

  /**
   * @param word
   * @return true if the given word ends with a vocal and y, false otherwise.
   */
  boolean endsWithVocalAndY(final String word);
}
