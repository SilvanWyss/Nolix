/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.stringtool;

/**
 * The {@link IStringTool} provides methods to handle {@link String}s.
 * 
 * @author Silvan Wyss
 */
public interface IStringTool {
  /**
   * @param string
   * @param n
   * @return a new {@link String} that is the given string without the last n
   *         characters
   * @throws RuntimeException if the given string is null
   * @throws RuntimeException if the given n is negative or bigger than the length
   *                          of the given string
   */
  String createStringWithoutLastCharacters(String string, int n);

  /**
   * @param tabCount
   * @return a new {@link String} consisting of as many tabulators as the given
   *         tabCount says
   * @throws RuntimeException if the given tabCount is negative
   */
  String createTabs(int tabCount);

  /**
   * @param object
   * @return the {@link String} representation of the given object in braces
   */
  String getInBraces(Object object);

  /**
   * @param objects an array of objects, is considered to be empty when is null
   * @return the {@link String} representation of the given objects in parentheses
   */
  String getInParentheses(Object... objects);

  /**
   * @param object
   * @return the {@link String} representation of the given object in single
   *         quotes
   */
  String getInSingleQuotes(Object object);

  /**
   * @param string
   * @return the boolean the given string represents
   * @throws RuntimeException if the given string does not represent a boolean
   */
  boolean toBoolean(String string);

  /**
   * @param string
   * @return a new {@link String} in capital snake case from the given string
   */
  String toCapitalSnakeCase(String string);

  /**
   * @param string
   * @return the double the given string represents.
   * @throws RuntimeException if the given string does not represent a double
   */
  double toDouble(String string);

  /**
   * @param string
   * @return a new {@link String} in Pascal case from the given string
   */
  String toPascalCase(String string);

  /**
   * @param string
   * @return a proportion in [0, 1] from the given string.
   * @throws RuntimeException if the given string does not represent a proportion
   * 
   */
  double toProportion(String string);
}
