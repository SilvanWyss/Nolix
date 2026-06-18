/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.stringexaminer;

/**
 * @author Silvan Wyss
 */
public interface IStringExaminer {
  /**
   * @param string
   * @return true if the given string is in lower case, false otherwise
   */
  boolean isLowerCase(String string);

  /**
   * @param string
   * @param prefix
   * @return true if the given string starts with the given prefix ignoring case,
   *         false otherwise
   */
  boolean startsWithIgnoringCase(String string, String prefix);
}
