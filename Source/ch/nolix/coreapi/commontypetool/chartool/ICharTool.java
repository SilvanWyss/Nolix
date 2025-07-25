package ch.nolix.coreapi.commontypetool.chartool;

/**
 * The {@link ICharTool} provides methods to handle chars.
 * 
 * @author Silvan Wyss
 * @version 2024-07-21
 */
public interface ICharTool {

  /**
   * @param character
   * @return true if the given character is a digit.
   */
  boolean isDigit(char character);

  /**
   * @param character
   * @return true if the given character is a hexadecimal digit.
   */
  boolean isHexadecimalDigit(char character);
}
