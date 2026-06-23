/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.base;

import java.util.function.Supplier;

//JUnit import
import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.datamodel.dataobject.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
final class ArgumentCaptorTest extends StandardTest {
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNull() {
    //execution & verification
    expectRunning(() -> //
    new ArgumentCaptor<String, VoidObject>(null) {
      //This class is just a sub class without additional methods.
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNotValid() {
    //execution & verification
    expectRunning(() -> //
    new ArgumentCaptor<String, VoidObject>(new VoidObject()) {
      //This class is just a sub class without additional methods.
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_defaultConstructor() {
    //execution & verification
    expectRunning(() -> //
    new ArgumentCaptor<String, VoidObject>() {
      //This class is just a sub class without additional methods.
    }) //
      .doesNotThrowException();
  }

  @Test
  void testCase_nxtArgCpt_whenDoesNotHaveNextArgumentCaptor() {
    //setup
    final var testUnit = //
    new ArgumentCaptor<String, VoidObject>() {
      //This class is just a sub class without additional methods.
    };

    //execution & verification
    expectRunning(testUnit::nxtArgCpt)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_setBuilder_whenTheGivenBuilderIsNull() {
    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {
      public void publicSetBuilder(final Supplier<VoidObject> builder) {
        setBuilder(builder);
      }
    };

    //execution & verification
    expectRunning(() -> testUnit.publicSetBuilder(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given builder is null.");
  }

  @Test
  void testCase_setBuilder_whenHasAlreadyBuilder() {
    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {
      public void publicSetBuilder(final Supplier<VoidObject> builder) {
        setBuilder(builder);
      }
    };
    testUnit.publicSetBuilder(VoidObject::new);

    //execution & verification
    expectRunning(() -> testUnit.publicSetBuilder(VoidObject::new))
      .throwsException()
      .ofType(ArgumentHasAttributeException.class);
  }
}
