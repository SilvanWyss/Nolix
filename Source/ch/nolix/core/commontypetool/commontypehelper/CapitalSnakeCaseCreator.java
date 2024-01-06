//package declaration
package ch.nolix.core.commontypetool.commontypehelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.commontypetoolapi.commontypeproperty.CharacterType;
import ch.nolix.coreapi.commontypetoolapi.stringutilapi.CharacterCatalogue;
import ch.nolix.coreapi.commontypetoolapi.stringutilapi.StringCatalogue;

//class
final class CapitalSnakeCaseCreator {

  //method
  public String toCapitalSnakeCase(final String string) {

    if (string.isEmpty()) {
      return StringCatalogue.EMPTY_STRING;
    }

    return toCapitalSnakeCaseWhenStringNotEmpty(string);
  }

  //method
  private String toCapitalSnakeCaseWhenStringNotEmpty(final String string) {

    final var stringBuilder = new StringBuilder();

    final var firstCharacter = string.charAt(0);
    final var firstCharacterType = CharacterType.fromCharacter(firstCharacter);
    switch (firstCharacterType) {
      case LOWER_CASE_LETTER:
        stringBuilder.append(Character.toUpperCase(firstCharacter));
        break;
      case UPPER_CASE_LETTER:
        stringBuilder.append(firstCharacter);
        break;
      case UNDERSCORE:
        break;
      case OTHER:
        stringBuilder.append(firstCharacter);
    }

    var previousCharacterType = firstCharacterType;
    for (var i = 1; i < string.length(); i++) {

      final var character = string.charAt(i);
      final var characterType = CharacterType.fromCharacter(character);

      switch (characterType) {
        case LOWER_CASE_LETTER:
          stringBuilder.append(Character.toUpperCase(character));
          break;
        case UPPER_CASE_LETTER:

          if (previousCharacterType == CharacterType.LOWER_CASE_LETTER) {
            stringBuilder.append(CharacterCatalogue.UNDERSCORE);
          }

          stringBuilder.append(character);

          break;
        case UNDERSCORE, OTHER:
          stringBuilder.append(Character.toUpperCase(character));
          break;
        default:
          throw InvalidArgumentException.forArgument(characterType);
      }

      previousCharacterType = characterType;
    }

    return stringBuilder.toString();
  }
}
