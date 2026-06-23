/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.misc.englishlanguage;

/**
 * @author Silvan Wyss
 */
public interface IEnglishWordEndExaminer {
  /**
   * @param word
   * @return true if the given word ends with a vocal and y, false otherwise.
   */
  boolean endsWithVocalAndY(final String word);
}
