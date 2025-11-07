package ch.nolix.coreapi.commontypetool.charactertool;

/**
 * The {@link ICharacterTool} provides methods to handle chars.
 * 
 * @author Silvan Wyss
 * @version 2024-07-21
 */
public interface ICharacterTool {
  /**
   * @param character
   * @return true if the given character is a digit, false otherwise.
   */
  boolean isDigit(char character);

  /**
   * @param character
   * @return true if the given character is a hexadecimal digit, false otherwise.
   */
  boolean isHexadecimalDigit(char character);
}
