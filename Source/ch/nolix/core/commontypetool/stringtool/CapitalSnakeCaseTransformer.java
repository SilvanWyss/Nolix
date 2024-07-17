//package declaration
package ch.nolix.core.commontypetool.stringtool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.characterproperty.CharacterType;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
public final class CapitalSnakeCaseTransformer {

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
            stringBuilder.append(CharacterCatalogue.UNDERSCORE);
          }

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

  //method
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
        if (firstCharacter != CharacterCatalogue.UNDERSCORE) {
          stringBuilder.append(firstCharacter);
        }
        break;
    }

    return firstCharacterType;
  }
}
