//package declaration
package ch.nolix.coreapi.commontypetoolapi.characterproperty;

//own imports
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;

//enum
public enum CharacterType {
  UPPER_CASE_LETTER,
  LOWER_CASE_LETTER,
  NUMBER,
  OTHER;

  //static method
  public static CharacterType ofCharacter(final char character) {
    return switch (character) {
      case
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z',
      CharacterCatalogue.UPPERCASE_AE,
      CharacterCatalogue.UPPERCASE_OE,
      CharacterCatalogue.UPPERCASE_UE ->
        UPPER_CASE_LETTER;
      case
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      CharacterCatalogue.LOWER_CASE_AE,
      CharacterCatalogue.LOWER_CASE_OE,
      CharacterCatalogue.LOWER_CASE_UE ->
        LOWER_CASE_LETTER;
      case
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9' ->
        NUMBER;
      default ->
        OTHER;
    };
  }
}
