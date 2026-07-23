/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.characterexaminer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.commontype.characterexaminer.CharacterExaminer;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class CharacterExaminerTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' })
  void testCase_isDigit_whenTheGivenCharacterIsADigit(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isDigit(character);

    // verify
    expect(result).isTrue();
  }

  @ParameterizedTest
  @ValueSource(chars = { 'A', 'a', '%', '&', '.', ',', '(', ')' })
  void testCase_isDigit_whenTheGivenCharacterIsNotADigit(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isDigit(character);

    // verify
    expect(result).isFalse();
  }

  @ParameterizedTest
  @ValueSource(chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' })
  void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isHexadecimalDigit(character);

    // verify
    expect(result).isTrue();
  }

  @ParameterizedTest
  @ValueSource(chars = { 'G', 'g', '%', '&', '.', ',', '(', ')' })
  void testCase_isHexadecimalDigit_whenTheGivenCharacterIsNotAHexadecimalDigit(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isHexadecimalDigit(character);

    // verify
    expect(result).isFalse();
  }
}
