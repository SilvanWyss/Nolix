//package declaration
package ch.nolix.coreapi.commontypetoolapi.chartoolapi;

/**
 * The {@link ICharTool} provides methods to handle chars.
 * 
 * @author Silvan Wyss
 * @version 2024-07-21
 */
public interface ICharTool {

  //method declaration
  /**
   * @param character
   * @return true if the given character is a digit.
   */
  boolean isDigit(char character);

  //method declaration
  /**
   * @param character
   * @return true if the given character is a hexadecimal digit.
   */
  boolean isHexadecimalDigit(char character);
}
