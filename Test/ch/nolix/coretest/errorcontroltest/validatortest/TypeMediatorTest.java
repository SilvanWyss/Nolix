package ch.nolix.coretest.errorcontroltest.validatortest;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.TypeMediator;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

final class TypeMediatorTest extends StandardTest {

  @Test
  void testCase_isAbstract_whenTheGivenArgumentIsNotAbstract() {

    //setup
    final var testUnit = TypeMediator.forArgument(String.class);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' is not abstract.");
  }

  @Test
  void testCase_isAbstract_whenTheGivenArgumentIsAbstract() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(testUnit::isAbstract).doesNotThrowException();
  }

  @Test
  void testCase_isAbstract_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isClass_whenTheGivenArgumentIsAClass() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isClass).doesNotThrowException();
  }

  @Test
  void testCase_isClass_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not a class.");
  }

  @Test
  void testCase_isClass_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode' is not a class.");
  }

  @Test
  void testCase_isClass_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isConcrete_whenTheGivenArgumentIsNotConcrete() {

    //setup
    final var testUnit = TypeMediator.forArgument(AbstractList.class);

    //execution & verify
    expectRunning(testUnit::isConcrete)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.AbstractList' is not concrete.");
  }

  @Test
  void testCase_isConcrete_whenTheGivenArgumentIsConcrete() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isConcrete).doesNotThrowException();
  }

  @Test
  void testCase_isConcrete_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isConcrete)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isEnum_whenTheGivenArgumentIsAClas() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an enum.");
  }

  @Test
  void testCase_isEnum_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not an enum.");
  }

  @Test
  void testCase_isEnum_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isEnum).doesNotThrowException();
  }

  @Test
  void testCase_isEnum_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isImplementing_whenTheGivenArgumentDoesNotImplementTheGivenInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(String.class);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' does not implement java.lang.Iterable.");
  }

  @Test
  void testCase_isImplementing_whenTheGivenArgumentImplementsTheGivenInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class)).doesNotThrowException();
  }

  @Test
  void testCase_isImplementing_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isInterface_whenTheGivenArgumentIsAnInterface() {

    //setup
    final var testUnit = TypeMediator.forArgument(Iterable.class);

    //execution & verify
    expectRunning(testUnit::isInterface).doesNotThrowException();
  }

  @Test
  void testCase_isInterface_whenTheGivenArgumentIsAClass() {

    //setup
    final var testUnit = TypeMediator.forArgument(ArrayList.class);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an interface.");
  }

  @Test
  void testCase_isInterface_whenTheGivenArgumentIsAnEnum() {

    //setup
    final var testUnit = TypeMediator.forArgument(WriteMode.class);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode' is not an interface.");
  }

  @Test
  void testCase_isInterface_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = TypeMediator.forArgument(null);

    //execution & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isSubTypeOf_whenTheGivenArgumentIsNotASubTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSubTypeOf(ArrayList.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a sub type of java.util.ArrayList.");
  }

  @Test
  void testCase_isSubTypeOf_whenTheGivenArgumentIsASubTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSubTypeOf(Iterable.class)).doesNotThrowException();
  }

  @Test
  void testCase_isSuperTypeOf_whenTheGivenArgumentIsNotASuperTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSuperTypeOf(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a super type of java.lang.Iterable.");
  }

  @Test
  void testCase_isSuperTypeOf_whenTheGivenArgumentIsASuperTypeOfTheGivenType() {

    //setup
    final var testUnit = TypeMediator.forArgument(List.class);

    //execution & verify
    expectRunning(() -> testUnit.isSuperTypeOf(ArrayList.class)).doesNotThrowException();
  }
}
