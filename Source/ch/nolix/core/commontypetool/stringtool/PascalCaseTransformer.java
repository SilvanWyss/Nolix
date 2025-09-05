package ch.nolix.core.commontypetool.stringtool;

import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterType;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;

public final class PascalCaseTransformer {
  public String toPascalCase(final String string) {
    if (string.isEmpty()) {
      return StringCatalog.EMPTY_STRING;
    }

    return toPascalCaseWhenStringIsNotEmpty(string);
  }

  private String toPascalCaseWhenStringIsNotEmpty(final String string) {
    final var stringBuilder = new StringBuilder();

    final var firstCharacter = string.charAt(0);
    final var firstCharacterType = CharacterType.ofCharacter(firstCharacter);
    switch (firstCharacterType) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case LOWER_CASE_LETTER:
        stringBuilder.append(Character.toUpperCase(firstCharacter));
        break;
      default:
        stringBuilder.append(firstCharacter);
    }

    var previousCharacter = firstCharacter;
    var previousCharacterType = firstCharacterType;
    for (var i = 1; i < string.length(); i++) {
      final var character = string.charAt(i);
      final var characterType = CharacterType.ofCharacter(character);

      switch (characterType) {
        case LOWER_CASE_LETTER:
          stringBuilder.append(
            getTargetCharacterWhenSourceCharacterIsNotAtBeginAndLowerCaseLetter(character, previousCharacter,
              previousCharacterType));
          break;
        case UPPER_CASE_LETTER:
          stringBuilder.append(
            getTargetCharacterWhenSourceCharacterIsNotAtBeginAndUpperCaseLetter(
              character, previousCharacter, previousCharacterType));
          break;
        case NUMBER:
          stringBuilder.append(character);
          break;
        case OTHER:
          if (character != CharacterCatalog.UNDERSCORE) {
            stringBuilder.append(character);
          }
          break;
      }

      previousCharacter = character;
      previousCharacterType = characterType;
    }

    return stringBuilder.toString();
  }

  private char getTargetCharacterWhenSourceCharacterIsNotAtBeginAndLowerCaseLetter(
    final char sourceCharacter,
    final char previousCharacter,
    final CharacterType previousCharacterType) {
    if (previousCharacterType == CharacterType.NUMBER
    || previousCharacter == CharacterCatalog.UNDERSCORE
    || previousCharacterType == CharacterType.OTHER) {
      return Character.toUpperCase(sourceCharacter);
    }

    return sourceCharacter;
  }

  private char getTargetCharacterWhenSourceCharacterIsNotAtBeginAndUpperCaseLetter(
    final char sourceCharacter,
    final char previousCharacter,
    final CharacterType previousCharacterType) {
    if (previousCharacterType == CharacterType.LOWER_CASE_LETTER
    || previousCharacterType == CharacterType.NUMBER
    || previousCharacter == CharacterCatalog.UNDERSCORE
    || previousCharacterType == CharacterType.OTHER) {
      return sourceCharacter;
    }

    return Character.toLowerCase(sourceCharacter);
  }
}
