/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.object;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.StringMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonEmptyArgumentException;

/**
 * @author Silvan Wyss
 */
final class StringMediatorTest extends StandardTest {
  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsNull() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(null, "string");

    // execute & verify
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given string is null.");
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsEmpty() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("", "string");

    // execute & verify
    expectRunning(testUnit::isEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfASpace() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(" ", "string");

    // execute & verify
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given string is not empty.");
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfLetters() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("aaa", "string");

    // execute & verify
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given string 'aaa' is not empty.");
  }

  @Test
  void testCase_hasLength_whenTheGivenLengthIsNegative() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("aaa", "string");

    // execute & verify
    expectRunning(() -> testUnit.hasLength(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given length '-1' is negative.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsNull() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(null, "string");

    // execute & verify
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given string is null.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsShorterThanTheGivenLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("aaa", "string");

    // execute & verify
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'aaa' does not have the length 4.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentHasTheGivenLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("aaaa", "string");

    // execute & verify
    expectRunning(() -> testUnit.hasLength(4)).doesNotThrowException();
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsLongerThanTheGivenLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("aaaaa", "string");

    // execute & verify
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'aaaaa' does not have the length 4.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsNull() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(null, "string");

    // execute & verify
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given string is null.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsEmpty() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("", "string");

    // execute & verify
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(EmptyArgumentException.class)
      .withMessage("The given string is empty.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfASpace() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(" ", "string");

    // execute & verify
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfALetter() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("a", "string");

    // execute & verify
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsNull() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(null, "string");

    // execute & verify
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given string is null.");
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsEmpty() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("", "string");

    // execute & verify
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfASpace() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(" ", "string");

    // execute & verify
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfALetter() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("a", "string");

    // execute & verify
    expectRunning(testUnit::isNotBlank).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsShorterThanTheMaxLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("lorem", "string");

    // execute & verify
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentHasTheMaxLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("lorem ipsu", "string");

    // execute & verify
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsLongerThanTheMaxLength() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("lorem ipsum dolor",
      "string");

    // execute & verify
    expectRunning(() -> testUnit.isNotLongerThan(10))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'lorem ipsum dolor' is longer than 10.");
  }

  @Test
  void testCase_matches_whenTheGivenArgumentMatches() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("lore", "string");

    // execute & verify
    expectRunning(() -> testUnit.matches("....")).doesNotThrowException();
  }

  @Test
  void testCase_matches_whenTheGivenArgumentDoesNotMatch() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("lorem", "string");

    // execute & verify
    expectRunning(() -> testUnit.matches("...."))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'lorem' does not match the regular expression '....'.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentIsNull() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName(null, "string");

    // execute & verify
    expectRunning(() -> testUnit.startsWith("Mada"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given string is null.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentStartWithTheGivenPrefix() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("Madagascar", "string");

    // execute & verify
    expectRunning(() -> testUnit.startsWith("Mada")).doesNotThrowException();
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentEqualsTheGivenPrefix() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("Madagascar", "string");

    // execute & verify
    expectRunning(() -> testUnit.startsWith("Madagascar")).doesNotThrowException();
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_1() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("Madagascar", "string");

    // execute & verify
    expectRunning(() -> testUnit.startsWith("mada"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'Madagascar' does not start with the prefix 'mada'.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_2() {
    // setup
    final var testUnit = StringMediator.forArgumentAndArgumentName("Madagascar", "string");

    // execute & verify
    expectRunning(() -> testUnit.startsWith("Madu"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given string 'Madagascar' does not start with the prefix 'Madu'.");
  }
}
