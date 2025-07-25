package ch.nolix.coreapi.programatom.characterproperty;

import ch.nolix.coreapi.programatom.stringcatalog.CharacterCatalog;

public enum CharacterType {
  UPPER_CASE_LETTER,
  LOWER_CASE_LETTER,
  NUMBER,
  OTHER;

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
      CharacterCatalog.UPPERCASE_AE,
      CharacterCatalog.UPPERCASE_OE,
      CharacterCatalog.UPPERCASE_UE ->
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
      CharacterCatalog.LOWER_CASE_AE,
      CharacterCatalog.LOWER_CASE_OE,
      CharacterCatalog.LOWER_CASE_UE ->
        LOWER_CASE_LETTER;
      default ->
        ofNonLetterCharacter(character);
    };
  }

  private static CharacterType ofNonLetterCharacter(final char nonLetterCharacter) {
    return switch (nonLetterCharacter) {
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
