//package declaration
package ch.nolix.coretest.commontypetooltest.iteratorvalidatortest;

//Java imports
import java.util.Iterator;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class IteratorValidatorTest extends StandardTest {

  //method
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

  //method
  @Test
  void testCase_whenTheGivenIteratorDoesNotHaveNext() {

    //setup
    final var iterator = //
    new Iterator<VoidObject>() {

      //method
      @Override
      public boolean hasNext() {
        return false;
      }

      //method
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

  //method
  @Test
  void testCase_whenTheGivenIteratorHasNext() {

    //setup
    final var iterator = //
    new Iterator<VoidObject>() {

      //method
      @Override
      public boolean hasNext() {
        return true;
      }

      //method
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
