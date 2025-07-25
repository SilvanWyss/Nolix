package ch.nolix.coreapi.container.commoncontainer;

/**
 * @author Silvan Wyss
 * @version 2024-11-14
 */
public interface StringMappable {

  /**
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable}.
   */
  String toConcatenatedString();

  /**
   * @param separator
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable} with the given separator in
   *         between.
   */
  String toStringWithSeparator(char separator);

  /**
   * @param separator
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link StringMappable} with the given separator in
   *         between.
   * @throws RuntimeException if the given separator is null.
   */
  String toStringWithSeparator(String separator);
}
