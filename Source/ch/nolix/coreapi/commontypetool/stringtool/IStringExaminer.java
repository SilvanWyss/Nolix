package ch.nolix.coreapi.commontypetool.stringtool;

/**
 * @author Silvan Wyss
 * @version 2025-03-01
 */
public interface IStringExaminer {
  /**
   * @param string
   * @return true if the given string is in lower case, false otherwise.
   */
  boolean isLowerCase(String string);

  /**
   * @param string
   * @return true if the given string is in pascal case, false otherwise.
   */
  boolean isPascalCase(String string);

  /**
   * @param string
   * @param prefix
   * @return true if the given string starts with the given prefix ignoring case,
   *         false otherwise.
   */
  boolean startsWithIgnoringCase(String string, String prefix);
}
