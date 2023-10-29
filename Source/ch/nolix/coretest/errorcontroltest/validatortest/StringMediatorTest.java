//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class StringMediatorTest extends Test {

  //method
  @TestCase
  public void testCase_hasLength_whenTheGivenLengthIsNegative() {

    //setup
    final var testUnit = new StringMediator("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given length '-1' is negative.");
  }

  //method
  @TestCase
  public void testCase_hasLength_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @TestCase
  public void testCase_hasLength_whenTheGivenArgumentIsShorterThanTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaa' does not have the length 4.");
  }

  //method
  @TestCase
  public void testCase_hasLength_whenTheGivenArgumentHasTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4)).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_hasLength_whenTheGivenArgumentIsLongerThanTheGivenLength() {

    //setup
    final var testUnit = new StringMediator("aaaaa");

    //execution & verification
    expectRunning(() -> testUnit.hasLength(4))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'aaaaa' does not have the length 4.");
  }

  //method
  @TestCase
  public void testCase_isNotEmpty_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @TestCase
  public void testCase_isNotEmpty_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = new StringMediator("");

    //execution & verification
    expectRunning(testUnit::isNotEmpty)
      .throwsException()
      .ofType(EmptyArgumentException.class)
      .withMessage("The given argument is empty.");
  }

  //method
  @TestCase
  public void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = new StringMediator(" ");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isNotEmpty_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = new StringMediator("a");

    //execution & verification
    expectRunning(testUnit::isNotEmpty).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isNotBlank_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new StringMediator(null);

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given argument is null.");
  }

  //method
  @TestCase
  public void testCase_isNotBlank_whenTheGivenArgumentIsEmpty() {

    //setup
    final var testUnit = new StringMediator("");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_isNotBlank_whenTheGivenArgumentConsistsOfASpace() {

    //setup
    final var testUnit = new StringMediator(" ");

    //execution & verification
    expectRunning(testUnit::isNotBlank)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_isNotBlank_whenTheGivenArgumentConsistsOfALetter() {

    //setup
    final var testUnit = new StringMediator("a");

    //execution & verification
    expectRunning(testUnit::isNotBlank).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isNotLongerThan_whenTheArgumentIsShorterThanTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isNotLongerThan_whenTheArgumentHasTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem ipsu");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isNotLongerThan_whenTheArgumentIsLongerThanTheMaxLength() {

    //setup
    final var testUnit = new StringMediator("lorem ipsum dolor");

    //execution & verification
    expectRunning(() -> testUnit.isNotLongerThan(10))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument 'lorem ipsum dolor' is longer than 10.");
  }
}
