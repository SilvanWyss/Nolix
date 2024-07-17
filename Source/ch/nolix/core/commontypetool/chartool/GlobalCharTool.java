//package declaration
package ch.nolix.core.commontypetool.chartool;

//class
/**
 * The {@link GlobalCharTool} provides methods to handle characters. Of the
 * {@link GlobalCharTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class GlobalCharTool {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalCharTool} can be created.
   */
  private GlobalCharTool() {
  }

  //static method
  /**
   * @param character
   * @return true if the given character is a digit.
   */
  public static boolean isDigit(final char character) {
    return (character >= 48 && character <= 57);
  }

  //static method
  /**
   * @param character
   * @return true if the given character is a hexadecimal digit.
   */
  public static boolean isHexadecimalDigit(final char character) {
    return (isDigit(character) || (character >= 65 && character <= 70));
  }
}
