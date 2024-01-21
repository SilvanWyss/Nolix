//package declaration
package ch.nolix.core.commontypetool;

import ch.nolix.coreapi.programatomapi.characterproperty.CharacterType;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
final class PascalCaseCreator {

  //method
  public String toPascalCase(final String string) {

    if (string.isEmpty()) {
      return StringCatalogue.EMPTY_STRING;
    }

    return toPascalCaseWhenStringNotEmpty(string);
  }

  //method
  private String toPascalCaseWhenStringNotEmpty(final String string) {

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
          if (previousCharacter == CharacterCatalogue.UNDERSCORE) {
            stringBuilder.append(Character.toUpperCase(character));
          } else {
            stringBuilder.append(character);
          }
          break;
        case UPPER_CASE_LETTER:
          if (previousCharacterType == CharacterType.LOWER_CASE_LETTER
          || previousCharacter == CharacterCatalogue.UNDERSCORE) {
            stringBuilder.append(character);
          } else {
            stringBuilder.append(Character.toLowerCase(character));
          }
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
}
