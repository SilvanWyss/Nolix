package ch.nolix.core.commontypetool.charactertool;

import ch.nolix.coreapi.commontypetool.charactertool.ICharacterTool;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class CharacterTool implements ICharacterTool {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDigit(final char character) {
    return (character >= 48 && character <= 57);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isHexadecimalDigit(final char character) {
    return (isDigit(character) || (character >= 65 && character <= 70));
  }
}
