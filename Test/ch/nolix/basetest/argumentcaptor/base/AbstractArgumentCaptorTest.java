/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.base;

import java.util.function.Supplier;

//JUnit import
import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.util.VoidObject;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
final class AbstractArgumentCaptorTest extends StandardTest {
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNull() {
    // execute & verify
    expectRunning(() -> //
    new AbstractArgumentCaptor<String, VoidObject>(null) {
      // This class is a sub class without additional methods.
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNotValid() {
    // execute & verify
    expectRunning(() -> //
    new AbstractArgumentCaptor<String, VoidObject>(new VoidObject()) {
      // This class is a sub class without additional methods.
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_defaultConstructor() {
    // execute & verify
    expectRunning(() -> //
    new AbstractArgumentCaptor<String, VoidObject>() {
      // This class is a sub class without additional methods.
    }) //
      .doesNotThrowException();
  }

  @Test
  void testCase_nxtArgCpt_whenDoesNotHaveNextArgumentCaptor() {
    // setup
    final var testUnit = //
    new AbstractArgumentCaptor<String, VoidObject>() {
      // This class is a sub class without additional methods.
    };

    // execute & verify
    expectRunning(testUnit::scsArgCpt)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_setBuilder_whenTheGivenBuilderIsNull() {
    // setup
    final var testUnit = new AbstractArgumentCaptor<String, VoidObject>() {
      public void publicSetBuilder(final Supplier<VoidObject> builder) {
        setBuilder(builder);
      }
    };

    // execute & verify
    expectRunning(() -> testUnit.publicSetBuilder(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given builder is null.");
  }

  @Test
  void testCase_setBuilder_whenHasAlreadyBuilder() {
    // setup
    final var testUnit = new AbstractArgumentCaptor<String, VoidObject>() {
      public void publicSetBuilder(final Supplier<VoidObject> builder) {
        setBuilder(builder);
      }
    };
    testUnit.publicSetBuilder(VoidObject::new);

    // execute & verify
    expectRunning(() -> testUnit.publicSetBuilder(VoidObject::new))
      .throwsException()
      .ofType(ArgumentHasAttributeException.class);
  }
}
