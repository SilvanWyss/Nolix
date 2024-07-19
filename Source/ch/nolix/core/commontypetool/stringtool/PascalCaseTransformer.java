//package declaration
package ch.nolix.core.commontypetool.stringtool;

//own imports
import ch.nolix.coreapi.programatomapi.characterproperty.CharacterType;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
public final class PascalCaseTransformer {

  //method
  public String toPascalCase(final String string) {

    if (string.isEmpty()) {
      return StringCatalogue.EMPTY_STRING;
    }

    return toPascalCaseWhenStringIsNotEmpty(string);
  }

  //method
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
          if (character != CharacterCatalogue.UNDERSCORE) {
            stringBuilder.append(character);
          }
          break;
      }

      previousCharacter = character;
      previousCharacterType = characterType;
    }

    return stringBuilder.toString();
  }

  //method
  private char getTargetCharacterWhenSourceCharacterIsNotAtBeginAndLowerCaseLetter(
    final char sourceCharacter,
    final char previousCharacter,
    final CharacterType previousCharacterType) {

    if (previousCharacterType == CharacterType.NUMBER
    || previousCharacter == CharacterCatalogue.UNDERSCORE
    || previousCharacterType == CharacterType.OTHER) {
      return Character.toUpperCase(sourceCharacter);
    }

    return sourceCharacter;
  }

  //method
  private char getTargetCharacterWhenSourceCharacterIsNotAtBeginAndUpperCaseLetter(
    final char sourceCharacter,
    final char previousCharacter,
    final CharacterType previousCharacterType) {

    if (previousCharacterType == CharacterType.LOWER_CASE_LETTER
    || previousCharacterType == CharacterType.NUMBER
    || previousCharacter == CharacterCatalogue.UNDERSCORE
    || previousCharacterType == CharacterType.OTHER) {
      return sourceCharacter;
    }

    return Character.toLowerCase(sourceCharacter);
  }
}
