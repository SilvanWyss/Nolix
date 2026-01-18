/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.stringtool;

import ch.nolix.coreapi.commontypetool.stringtool.IStringTool;

/**
 * The {@link StringTool} provides methods to handle {@link String}s.
 * 
 * Of the {@link StringTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class StringTool {
  private static final IStringTool STRING_TOOL_UNIT = new StringToolUnit();

  /**
   * Prevents that an instance of the {@link StringTool} can be created.
   */
  private StringTool() {
  }

  /**
   * @param string
   * @param n
   * @return a new {@link String} that is like the given string without the last n
   *         characters.
   */
  public static String createStringWithoutLastCharacters(final String string, final int n) {
    return STRING_TOOL_UNIT.createStringWithoutLastCharacters(string, n);
  }

  /**
   * @param tabCount
   * @return a new {@link String} consisting of as many tabulators as the given
   *         tabCount says.
   * @throws RuntimeException if the given tabCount is negative.
   */
  public static String createTabs(final int tabCount) {
    return STRING_TOOL_UNIT.createTabs(tabCount);
  }

  /**
   * @param object
   * @return the {@link String} representation of the given object in braces.
   * @throws RuntimeException if the given object is null.
   */
  public static String getInBraces(final Object object) {
    return STRING_TOOL_UNIT.getInBraces(object);
  }

  /**
   * @param object
   * @param objects
   * @return the {@link String} representation of the given object and objects in
   *         parentheses.
   * @throws RuntimeException if the given object is null.
   * @throws RuntimeException if the given objects is null.
   * @throws RuntimeException if one of the given objects is null.
   */
  public static String getInParentheses(final Object object, final Object... objects) {
    return STRING_TOOL_UNIT.getInParentheses(object, objects);
  }

  /**
   * @param object
   * @return the {@link String} representation of the given object in single
   *         quotes.
   * @throws RuntimeException if the given object is null.
   */
  public static String getInSingleQuotes(final Object object) {
    return STRING_TOOL_UNIT.getInSingleQuotes(object);
  }

  /**
   * @param string
   * @return the boolean the given string represents.
   * @throws RuntimeException if the given string does not represent a boolean.
   */
  public static boolean toBoolean(final String string) {
    return STRING_TOOL_UNIT.toBoolean(string);
  }

  /**
   * @param string
   * @return a new {@link String} in capital snake case for the given string.
   */
  public static String toCapitalSnakeCase(final String string) {
    return STRING_TOOL_UNIT.toCapitalSnakeCase(string);
  }

  /**
   * @param string
   * @return the double the given string represents.
   * @throws RuntimeException if the given string does not represent a double.
   */
  public static double toDouble(final String string) {
    return STRING_TOOL_UNIT.toDouble(string);
  }

  /**
   * @param string
   * @return a new {@link String} in pascal case for the given string.
   */
  public static String toPascalCase(String string) {
    return STRING_TOOL_UNIT.toPascalCase(string);
  }

  /**
   * @param string
   * @return a proportion in [0, 1] from the given string.
   * @throws RuntimeException if the given string does not represent a proportion.
   * 
   */
  public static double toProportion(String string) {
    return STRING_TOOL_UNIT.toProportion(string);
  }
}
