/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.characterexaminer;

/**
 * The {@link ICharacterExaminer} provides methods to handle chars.
 * 
 * @author Silvan Wyss
 */
public interface ICharacterExaminer {
  /**
   * @param character
   * @return true if the given character is a digit, false otherwise
   */
  boolean isDigit(char character);

  /**
   * @param character
   * @return true if the given character is a hexadecimal digit, false otherwise
   */
  boolean isHexadecimalDigit(char character);
}
