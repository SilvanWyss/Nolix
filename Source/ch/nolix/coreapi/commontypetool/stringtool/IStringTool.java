package ch.nolix.coreapi.commontypetool.stringtool;

/**
 * The {@link IStringTool} provides methods to handle {@link String}s.
 * 
 * @author Silvan Wyss
 * @version 2024-07-22
 */
public interface IStringTool {
  /**
   * @param string
   * @param n
   * @return a new {@link String} that is like the given string without the last n
   *         characters.
   */
  String createStringWithoutLastCharacters(String string, int n);

  /**
   * @param tabCount
   * @return a new {@link String} consisting of as many tabulators as the given
   *         tabCount says.
   * @throws RuntimeException if the given tabCount is negative.
   */
  String createTabs(int tabCount);

  /**
   * @param object
   * @return the {@link String} representation of the given object in braces.
   * @throws RuntimeException if the given object is null.
   */
  String getInBraces(Object object);

  /**
   * @param object
   * @param objects
   * @return the {@link String} representation of the given object and objects in
   *         parentheses.
   * @throws RuntimeException if the given object is null.
   * @throws RuntimeException if the given objects is null.
   * @throws RuntimeException if one of the given objects is null.
   */
  String getInParentheses(Object object, Object... objects);

  /**
   * @param object
   * @return the {@link String} representation of the given object in single
   *         quotes.
   * @throws RuntimeException if the given object is null.
   */
  String getInSingleQuotes(Object object);

  /**
   * @param string
   * @return the boolean the given string represents.
   * @throws RuntimeException if the given string does not represent a boolean.
   */
  boolean toBoolean(String string);

  /**
   * @param string
   * @return a new {@link String} in capital snake case for the given string.
   */
  String toCapitalSnakeCase(String string);

  /**
   * @param string
   * @return the double the given string represents.
   * @throws RuntimeException if the given string does not represent a double.
   */
  double toDouble(String string);

  /**
   * @param string
   * @return a new {@link String} in pascal case for the given string.
   */
  String toPascalCase(String string);
}
