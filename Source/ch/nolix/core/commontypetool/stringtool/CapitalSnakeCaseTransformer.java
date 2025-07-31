package ch.nolix.core.commontypetool.stringtool;

import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterType;
import ch.nolix.coreapi.programatom.stringcatalog.StringCatalog;

public final class CapitalSnakeCaseTransformer {

  public String toCapitalSnakeCase(final String string) {

    if (string.isEmpty()) {
      return StringCatalog.EMPTY_STRING;
    }

    return toCapitalSnakeCaseWhenStringNotEmpty(string);
  }

  private String toCapitalSnakeCaseWhenStringNotEmpty(final String string) {

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
        case NUMBER:
          stringBuilder.append(character);
          break;
        case OTHER:
          stringBuilder.append(Character.toUpperCase(character));
          break;
      }

      previousCharacterType = characterType;
    }

    return stringBuilder.toString();
  }

  private CharacterType appendFirstTargetCharacterAccordingToFirstCharacterAndGetFirstCharacterType(
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
      case NUMBER:
        stringBuilder.append(firstCharacter);
        break;
      case OTHER:
        if (firstCharacter != CharacterCatalog.UNDERSCORE) {
          stringBuilder.append(firstCharacter);
        }
        break;
    }

    return firstCharacterType;
  }
}
