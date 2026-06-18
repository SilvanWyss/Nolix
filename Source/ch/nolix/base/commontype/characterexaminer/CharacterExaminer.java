/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.characterexaminer;

import ch.nolix.baseapi.commontype.characterexaminer.ICharacterExaminer;

/**
 * @author Silvan Wyss
 */
public final class CharacterExaminer implements ICharacterExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDigit(final char character) {
    return character >= 48 && character <= 57;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isHexadecimalDigit(final char character) {
    return isDigit(character) || (character >= 65 && character <= 70);
  }
}
