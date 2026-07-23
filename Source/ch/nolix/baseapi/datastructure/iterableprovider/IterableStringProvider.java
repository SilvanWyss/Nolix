/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableprovider;

/**
 * @author Silvan Wyss
 */
public interface IterableStringProvider {
  /**
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link IterableStringProvider}.
   */
  String toConcatenatedString();

  /**
   * @param delimiter
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link IterableStringProvider} with the given
   *         separator in between.
   */
  String toStringWithDelimiter(char delimiter);

  /**
   * @param delimiter
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link IterableStringProvider} with the given
   *         separator in between
   * @throws RuntimeException if the given separator is null
   */
  String toStringWithDelimiter(String delimiter);
}
