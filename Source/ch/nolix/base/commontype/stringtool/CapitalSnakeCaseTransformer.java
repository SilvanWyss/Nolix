/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.stringtool;

import ch.nolix.baseapi.commontype.charactertool.CharacterCatalog;
import ch.nolix.baseapi.commontype.charactertool.CharacterType;
import ch.nolix.baseapi.commontype.stringtool.StringCatalog;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public final class CapitalSnakeCaseTransformer {
  private CapitalSnakeCaseTransformer() {
  }

  public static String toCapitalSnakeCase(final String string) {
    if (string.isEmpty()) {
      return StringCatalog.EMPTY_STRING;
    }

    return toCapitalSnakeCaseWhenStringNotEmpty(string);
  }

  private static String toCapitalSnakeCaseWhenStringNotEmpty(final String string) {
    final var stringBuilder = new StringBuilder();
    final var firstCharacter = string.charAt(0);

    final var firstCharacterType = appendFirstTargetCharacterAccordingToFirstCharacterAndGetFirstCharacterType(
      firstCharacter,
      stringBuilder);

    var previousCharacterType = firstCharacterType;
    for (var i = 1; i < string.length(); i++) {
      final var character = string.charAt(i);
      final var characterType = CharacterType.ofCharacter(character);

      switch (characterType) {
        case LOWER_CASE_LETTER:
          stringBuilder.append(Character.toUpperCase(character));
          break;
        case UPPER_CASE_LETTER:

          if (previousCharacterType == CharacterType.LOWER_CASE_LETTER) {
            stringBuilder.append(CharacterCatalog.UNDERSCORE);
          }

          stringBuilder.append(character);

          break;
        case DIGIT:
          stringBuilder.append(character);
          break;
        case OTHER:
          stringBuilder.append(Character.toUpperCase(character));
          break;
        default:
          throw InvalidArgumentException.forArgument(characterType);
      }

      previousCharacterType = characterType;
    }

    return stringBuilder.toString();
  }

  private static CharacterType appendFirstTargetCharacterAccordingToFirstCharacterAndGetFirstCharacterType(
    final char firstCharacter,
    final StringBuilder stringBuilder) {
    final var firstCharacterType = CharacterType.ofCharacter(firstCharacter);

    switch (firstCharacterType) {
      case LOWER_CASE_LETTER:
        stringBuilder.append(Character.toUpperCase(firstCharacter));
        break;
      case UPPER_CASE_LETTER:
        stringBuilder.append(firstCharacter);
        break;
      case DIGIT:
        stringBuilder.append(firstCharacter);
        break;
      case OTHER:
        if (firstCharacter != CharacterCatalog.UNDERSCORE) {
          stringBuilder.append(firstCharacter);
        }

        break;
      default:
        throw InvalidArgumentException.forArgument(firstCharacterType);
    }

    return firstCharacterType;
  }
}
