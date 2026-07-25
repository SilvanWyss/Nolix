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
final class IsVisibleTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(chars = { 'A', 'a', 'X', 'x', '0', '9', '+', '/' })
  void testCase_isVisible_whenIsVisible(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isVisible(character);

    // verify
    expect(result).isTrue();
  }

  @ParameterizedTest
  @ValueSource(chars = { 0, 32, ' ', '\t', '\n' })
  void testCase_isVisible_whenIsNotVisible(final char character) {
    // setup   
    final var testUnit = new CharacterExaminer();

    // execute
    final var result = testUnit.isVisible(character);

    // verify
    expect(result).isFalse();
  }
}
