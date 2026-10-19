/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.object;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.NamableTypeMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.programcontrol.processproperty.WriteMode;

/**
 * @author Silvan Wyss
 */
final class NamableTypeMediatorTest extends StandardTest {
  @Test
  void testCase_isAbstract_whenGivenArgumentIsNotAbstract() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(String.class);

    // execute & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' is not abstract.");
  }

  @Test
  void testCase_isAbstract_whenGivenArgumentIsAbstract() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(List.class);

    // execute & verify
    expectRunning(testUnit::isAbstract).doesNotThrowException();
  }

  @Test
  void testCase_isAbstract_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isClass_whenGivenArgumentIsAClass() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(ArrayList.class);

    // execute & verify
    expectRunning(testUnit::isClass).doesNotThrowException();
  }

  @Test
  void testCase_isClass_whenGivenArgumentIsAnInterface() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(Iterable.class);

    // execute & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not a class.");
  }

  @Test
  void testCase_isClass_whenGivenArgumentIsAnEnum() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(WriteMode.class);

    // execute & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.baseapi.programcontrol.processproperty.WriteMode' is not a class.");
  }

  @Test
  void testCase_isClass_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(testUnit::isClass)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isConcrete_whenGivenArgumentIsNotConcrete() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(AbstractList.class);

    // execute & verify
    expectRunning(testUnit::isConcrete)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.AbstractList' is not concrete.");
  }

  @Test
  void testCase_isConcrete_whenGivenArgumentIsConcrete() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(ArrayList.class);

    // execute & verify
    expectRunning(testUnit::isConcrete).doesNotThrowException();
  }

  @Test
  void testCase_isConcrete_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(testUnit::isConcrete)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isEnum_whenGivenArgumentIsAClas() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(ArrayList.class);

    // execute & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an enum.");
  }

  @Test
  void testCase_isEnum_whenGivenArgumentIsAnInterface() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(Iterable.class);

    // execute & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.lang.Iterable' is not an enum.");
  }

  @Test
  void testCase_isEnum_whenGivenArgumentIsAnEnum() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(WriteMode.class);

    // execute & verify
    expectRunning(testUnit::isEnum).doesNotThrowException();
  }

  @Test
  void testCase_isEnum_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(testUnit::isEnum)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isImplementing_whenGivenArgumentDoesNotImplementTheGivenInterface() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(String.class);

    // execute & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' does not implement java.lang.Iterable.");
  }

  @Test
  void testCase_isImplementing_whenGivenArgumentImplementsTheGivenInterface() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(ArrayList.class);

    // execute & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class)).doesNotThrowException();
  }

  @Test
  void testCase_isImplementing_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(() -> testUnit.isImplementing(Iterable.class))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isInterface_whenGivenArgumentIsAnInterface() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(Iterable.class);

    // execute & verify
    expectRunning(testUnit::isInterface).doesNotThrowException();
  }

  @Test
  void testCase_isInterface_whenGivenArgumentIsAClass() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(ArrayList.class);

    // execute & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.util.ArrayList' is not an interface.");
  }

  @Test
  void testCase_isInterface_whenGivenArgumentIsAnEnum() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(WriteMode.class);

    // execute & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given type 'class ch.nolix.baseapi.programcontrol.processproperty.WriteMode' is not an interface.");
  }

  @Test
  void testCase_isInterface_whenGivenArgumentIsNull() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(null);

    // execute & verify
    expectRunning(testUnit::isInterface)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  @Test
  void testCase_isSubTypeOf_whenGivenArgumentIsNotASubTypeOfTheGivenType() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(List.class);

    // execute & verify
    expectRunning(() -> testUnit.isSubTypeOf(ArrayList.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a sub type of java.util.ArrayList.");
  }

  @Test
  void testCase_isSubTypeOf_whenGivenArgumentIsASubTypeOfTheGivenType() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(List.class);

    // execute & verify
    expectRunning(() -> testUnit.isSubTypeOf(Iterable.class)).doesNotThrowException();
  }

  @Test
  void testCase_isSuperTypeOf_whenGivenArgumentIsNotASuperTypeOfTheGivenType() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(List.class);

    // execute & verify
    expectRunning(() -> testUnit.isSuperTypeOf(Iterable.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'interface java.util.List' is not a super type of java.lang.Iterable.");
  }

  @Test
  void testCase_isSuperTypeOf_whenGivenArgumentIsASuperTypeOfTheGivenType() {
    // setup
    final var testUnit = NamableTypeMediator.forArgument(List.class);

    // execute & verify
    expectRunning(() -> testUnit.isSuperTypeOf(ArrayList.class)).doesNotThrowException();
  }
}
