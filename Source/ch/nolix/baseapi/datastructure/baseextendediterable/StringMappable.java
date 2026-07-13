/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.baseextendediterable;

/**
 * @author Silvan Wyss
 */
public interface StringMappable {
  /**
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable}.
   */
  String toConcatenatedString();

  /**
   * @param delimiter
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable} with the given separator in
   *         between.
   */
  String toStringWithDelimiter(char delimiter);

  /**
   * @param delimiter
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable} with the given separator in
   *         between.
   * @throws RuntimeException if the given separator is null.
   */
  String toStringWithDelimiter(String delimiter);
}
