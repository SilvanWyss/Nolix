/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.englishlanguage;

/**
 * @author Silvan Wyss
 */
public interface IEnglishWordEndExaminer {
  /**
   * @param word
   * @return true if the given word ends with a vocal and y, false otherwise
   */
  boolean endsWithVocalAndY(final String word);
}
