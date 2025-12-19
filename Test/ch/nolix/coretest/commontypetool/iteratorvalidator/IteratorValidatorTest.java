package ch.nolix.coretest.commontypetool.iteratorvalidator;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class IteratorValidatorTest extends StandardTest {
  @Test
  void testCase_whenTheGivenIteratorIsNull() {
    //setup
    final var testUnit = new IteratorValidator();

    //execution & verification
    expectRunning(() -> testUnit.assertHasNext(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Iterator is null.");
  }

  @Test
  void testCase_whenTheGivenIteratorDoesNotHaveNext() {
    //setup
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

    //execution & verification
    expectRunning(() -> testUnit.assertHasNext(iterator))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_whenTheGivenIteratorHasNext() {
    //setup
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

    //execution & verification
    expectRunning(() -> testUnit.assertHasNext(iterator)).doesNotThrowException();
  }
}
