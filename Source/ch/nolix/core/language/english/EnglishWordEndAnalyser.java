package ch.nolix.core.language.english;

import ch.nolix.coreapi.languageapi.englishapi.IEnglishWordEndAnalyser;

/**
 * @author Silvan Wyss
 * @version 2023-12-17
 */
public final class EnglishWordEndAnalyser implements IEnglishWordEndAnalyser {

  /**
   * @param word
   * @return true if the given word ends with a vocal and y, false otherwise, for
   *         the case that the given word is not null.
   */
  private static boolean endsWithVocalAndYWhenIsNotNull(final String word) {
    return //
    word.endsWith("ay") //NOSONAR: This implementation is uniform.
    || word.endsWith("ey")
    || word.endsWith("iy")
    || word.endsWith("oy")
    || word.endsWith("uy");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean endsWithVocalAndY(final String word) {
    return //
    word != null
    && endsWithVocalAndYWhenIsNotNull(word);
  }
}
