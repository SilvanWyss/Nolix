/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iteratorvalidator;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iteratorvalidator.IteratorValidator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.testing.testutil.VoidObject;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class IteratorValidatorTest extends StandardTest {
  @Test
  void testCase_whenTheGivenIteratorIsNull() {
    // setup
    final var testUnit = new IteratorValidator();

    // execute & verify
    expectRunning(() -> testUnit.assertHasNext(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Iterator is null.");
  }

  @Test
  void testCase_whenTheGivenIteratorDoesNotHaveNext() {
    // setup
    final var iterator = //
    new Iterator<VoidObject>() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public VoidObject next() {
        throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "next");
      }
    };
    final var testUnit = new IteratorValidator();

    // execute & verify
    expectRunning(() -> testUnit.assertHasNext(iterator))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_whenTheGivenIteratorHasNext() {
    // setup
    final var iterator = //
    new Iterator<VoidObject>() {
      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public VoidObject next() {
        return new VoidObject();
      }
    };
    final var testUnit = new IteratorValidator();

    // execute & verify
    expectRunning(() -> testUnit.assertHasNext(iterator)).doesNotThrowException();
  }
}
