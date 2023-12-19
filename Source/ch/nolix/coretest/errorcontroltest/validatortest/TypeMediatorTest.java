//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//Java imports
import java.util.ArrayList;
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.TypeMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
public final class TypeMediatorTest extends Test {

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsNotAbstract() {

    //setup
    final var testUnit = TypeMediator.forArgument(String.class);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' is not abstract.");
  }

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsAbstract() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(testUnit::isAbstract).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isClass_whenTheGivenArgumentIsAClass() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isClass).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isClass_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not a class.");
  }

  //method
  @TestCase
  public void testCase_isClass_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode' is not a class.");
  }

  //method
  @TestCase
  public void testCase_isClass_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isEnum_whenTheGivenArgumentIsAClas() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an enum.");
  }

  //method
  @TestCase
  public void testCase_isEnum_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not an enum.");
  }

  //method
  @TestCase
  public void testCase_isEnum_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isEnum).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isEnum_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isImplementing_whenTheGivenArgumentImplementsTheGivenInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class)).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isImplementing_whenTheGivenArgumentDoesNotImplementTheGivenInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(String.class);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' does not implement java.lang.Iterable.");
  }

  //method
  @TestCase
  public void testCase_isImplementing_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isInterface_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isInterface).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isInterface_whenTheGivenArgumentIsAClass() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an interface.");
  }

  //method
  @TestCase
  public void testCase_isInterface_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode' is not an interface.");
  }

  //method
  @TestCase
  public void testCase_isInterface_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isSubTypeOf_whenTheGivenArgumentIsNotASubTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSubTypeOf(ArrayList.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a sub type of java.util.ArrayList.");
  }

  //method
  @TestCase
  public void testCase_isSubTypeOf_whenTheGivenArgumentIsASubTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSubTypeOf(Iterable.class)).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isSuperTypeOf_whenTheGivenArgumentIsNotASuperTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSuperTypeOf(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a super type of java.lang.Iterable.");
  }

  //method
  @TestCase
  public void testCase_isSuperTypeOf_whenTheGivenArgumentIsASuperTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSuperTypeOf(ArrayList.class)).doesNotThrowException();
  }
}