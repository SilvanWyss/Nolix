//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.core.testing.test.StandardTest;

//class
final class StringMediatorTest extends StandardTest {

  //method
  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @Test
  void testCase_isEmpty_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = new StringMediator("");

    //execution & verification
    expectRunning(testUnit::isEmpty).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = new StringMediator(" ");

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given argument is not empty.");
  }

  //method
  @Test
  void testCase_isEmpty_whenTheGivenArgumentConsistsOfLetters() {

    //setup
    final var testUnit = new StringMediator("aaa");

    //execution & verification
    expectRunning(testUnit::isEmpty)
      .throwsException()
      .ofType(NonEmptyArgumentException.class)
      .withMessage("The given argument 'aaa' is not empty.");
  }

  //method
  @Test
  void testCase_hasLength_whenTheGivenLengthIsNegative() {

    //setup
    final var testUnit = new StringMediator("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given length '-1' is negative.");
  }

  //method
  @Test
  void testCase_hasLength_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @Test
  void testCase_hasLength_whenTheGivenArgumentIsShorterThanTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaa' does not have the length 4.");
  }

  //method
  @Test
  void testCase_hasLength_whenTheGivenArgumentHasTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4)).doesNotThrowException();
  }

  //method
  @Test
  void testCase_hasLength_whenTheGivenArgumentIsLongerThanTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaaaa' does not have the length 4.");
  }

  //method
  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = new StringMediator("");

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(EmptyArgumentException.class)
      .withMessage("The given argument is empty.");
  }

  //method
  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = new StringMediator(" ");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = new StringMediator("a");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @Test
  void testCase_isNotBlank_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = new StringMediator("");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = new StringMediator(" ");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_isNotBlank_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = new StringMediator("a");

    //execution & verification
    expectRunning(testUnit::isNotBlank).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsShorterThanTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isNotLongerThan_whenTheArgumentHasTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem ipsu");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  //method
  @Test
  void testCase_isNotLongerThan_whenTheArgumentIsLongerThanTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem ipsum dolor");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'lorem ipsum dolor' is longer than 10.");
  }

  //method
  @Test
  void testCase_startsWith_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Mada"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @Test
  void testCase_startsWith_whenTheGivenArgumentStartWithTheGivenPrefix() {

    //setup
    final var testUnit = new StringMediator("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Mada")).doesNotThrowException();
  }

  //method
  @Test
  void testCase_startsWith_whenTheGivenArgumentEqualsTheGivenPrefix() {

    //setup
    final var testUnit = new StringMediator("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Madagascar")).doesNotThrowException();
  }

  //method
  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_1() {

    //setup
    final var testUnit = new StringMediator("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("mada"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'Madagascar' does not start with the prefix 'mada'.");
  }

  //method
  @Test
  void testCase_startsWith_whenTheGivenArgumentDoesNotStartWithTheGivenPrefix_2() {

    //setup
    final var testUnit = new StringMediator("Madagascar");

    //execution & verification
    expectRunning(() -> testUnit.startsWith("Madu"))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'Madagascar' does not start with the prefix 'Madu'.");
  }
}
