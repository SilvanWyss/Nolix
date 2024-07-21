//package declaration
package ch.nolix.core.commontypetool.chartool;

//own imports
import ch.nolix.coreapi.commontypetoolapi.chartoolapi.ICharTool;

//class
/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class CharTool implements ICharTool {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDigit(final char character) {
    return (character >= 48 && character <= 57);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isHexadecimalDigit(final char character) {
    return (isDigit(character) || (character >= 65 && character <= 70));
  }
}
