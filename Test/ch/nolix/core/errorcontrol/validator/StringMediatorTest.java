package ch.nolix.core.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class StringMediatorTest extends StandardTest {

  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = StringMediator.forArgument(null);

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = StringMediator.forArgument("");

    //execution & verification
    expectRunning(testUnit::isEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = StringMediator.forArgument(" ");

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given argument is not empty.");
  }

  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfLetters() {

    //setup
    final var testUnit = StringMediator.forArgument("aaa");

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given argument 'aaa' is not empty.");
  }

  @Test
  void testCase_hasLength_whenTheGivenLengthIsNegative() {

    //setup
    final var testUnit = StringMediator.forArgument("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given length '-1' is negative.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = StringMediator.forArgument(null);

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsShorterThanTheGivenLength() {

    //setup
    final var testUnit = StringMediator.forArgument("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaa' does not have the length 4.");
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentHasTheGivenLength() {

    //setup
    final var testUnit = StringMediator.forArgument("aaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4)).doesNotThrowException();
  }

  @Test
  void testCase_hasLength_whenTheGivenArgumentIsLongerThanTheGivenLength() {

    //setup
    final var testUnit = StringMediator.forArgument("aaaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaaaa' does not have the length 4.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = StringMediator.forArgument(null);

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = StringMediator.forArgument("");

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(EmptyArgumentException.class)
      .withMessage("The given argument is empty.");
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = StringMediator.forArgument(" ");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = StringMediator.forArgument("a");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = StringMediator.forArgument(null);

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = StringMediator.forArgument("");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = StringMediator.forArgument(" ");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = StringMediator.forArgument("a");

    //execution & verification
    expectRunning(testUnit::isNotBlank).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsShorterThanTheMaxLength() {

    //setup
    final var testUnit = StringMediator.forArgument("lorem");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentHasTheMaxLength() {

    //setup
    final var testUnit = StringMediator.forArgument("lorem ipsu");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsLongerThanTheMaxLength() {

    //setup
    final var testUnit = StringMediator.forArgument("lorem ipsum dolor");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'lorem ipsum dolor' is longer than 10.");
  }

  @Test
  void testCase_matches_whenTheGivenArgumentMatches() {

    //setup
    final var testUnit = StringMediator.forArgument("lore");

    //execution & verification
    expectRunning(() -> testUnit.matches("....")).doesNotThrowException();
  }

  @Test
  void testCase_matches_whenTheGivenArgumentDoesNotMatch() {

    //setup
    final var testUnit = StringMediator.forArgument("lorem");

    //execution & verification
    expectRunning(() -> testUnit.matches("...."))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'lorem' does not match the regular expression '....'.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = StringMediator.forArgument(null);

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Mada"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentStartWithTheGivenPrefix() {

    //setup
    final var testUnit = StringMediator.forArgument("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Mada")).doesNotThrowException();
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentEqualsTheGivenPrefix() {

    //setup
    final var testUnit = StringMediator.forArgument("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Madagascar")).doesNotThrowException();
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_1() {

    //setup
    final var testUnit = StringMediator.forArgument("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("mada"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'Madagascar' does not start with the prefix 'mada'.");
  }

  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_2() {

    //setup
    final var testUnit = StringMediator.forArgument("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Madu"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'Madagascar' does not start with the prefix 'Madu'.");
  }
}
