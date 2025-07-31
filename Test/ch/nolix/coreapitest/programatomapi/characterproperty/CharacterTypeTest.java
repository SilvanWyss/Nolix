package ch.nolix.coreapitest.programatomapi.characterproperty;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterType;

final class CharacterTypeTest extends StandardTest {

  @Test
  void testCase_ofCharacter_whenTheGivenCharacterIsANumber() {
    expect(CharacterType.ofCharacter('0')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('1')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('2')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('3')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('4')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('5')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('6')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('7')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('8')).is(CharacterType.NUMBER);
    expect(CharacterType.ofCharacter('9')).is(CharacterType.NUMBER);
  }

  @Test
  void testCase_ofCharacter_whenTheGivenCharacterIsALowerCaseLetter() { //NOSONAR: The number of assertions makes sense.

    expect(CharacterType.ofCharacter('a')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('b')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('c')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('d')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('e')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('f')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('g')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('h')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('i')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('j')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('k')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('l')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('m')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('n')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('o')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('p')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('q')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('r')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('s')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('t')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('u')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('v')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('w')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('x')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('y')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('z')).is(CharacterType.LOWER_CASE_LETTER);

    expect(CharacterType.ofCharacter('ä')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('ö')).is(CharacterType.LOWER_CASE_LETTER);
    expect(CharacterType.ofCharacter('ü')).is(CharacterType.LOWER_CASE_LETTER);
  }

  @Test
  void testCase_ofCharacter_whenTheGivenCharacterIsAnUpperCaseLetter() { //NOSONAR: The number of assertions makes sense.

    expect(CharacterType.ofCharacter('A')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('B')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('C')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('D')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('E')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('F')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('G')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('H')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('I')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('J')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('K')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('L')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('M')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('N')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('O')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('P')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('Q')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('R')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('S')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('T')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('U')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('V')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('W')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('X')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('Y')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('Z')).is(CharacterType.UPPER_CASE_LETTER);

    expect(CharacterType.ofCharacter('Ä')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('Ö')).is(CharacterType.UPPER_CASE_LETTER);
    expect(CharacterType.ofCharacter('Ü')).is(CharacterType.UPPER_CASE_LETTER);
  }

  @Test
  void testCase_ofCharacter_whenTheGivenCharacterIsASpecialCharacter() {

    expect(CharacterType.ofCharacter('.')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter(',')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter(':')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter(';')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('?')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('!')).is(CharacterType.OTHER);

    expect(CharacterType.ofCharacter('+')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('-')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('*')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('/')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('^')).is(CharacterType.OTHER);

    expect(CharacterType.ofCharacter('(')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter(')')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('[')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter(']')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('{')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('}')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('{')).is(CharacterType.OTHER);

    expect(CharacterType.ofCharacter('§')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('@')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('#')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('%')).is(CharacterType.OTHER);
    expect(CharacterType.ofCharacter('&')).is(CharacterType.OTHER);
  }
}
