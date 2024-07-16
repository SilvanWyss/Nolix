//package declaration
package ch.nolix.coretest.argumentcaptortest.basetest;

//Java imports
import java.util.function.Supplier;

//JUnit import
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ArgumentCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNull() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(null) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNotValid() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(new VoidObject()) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_defaultConstructor() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>() {
    }) //
      .doesNotThrowException();
  }

  //method
  @Test
  void testCase_nxtArgCpt_whenDoesNotHaveANextArgumentCaptor() {

    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {
    };

    //execution & verification
    expectRunning(testUnit::nxtArgCpt)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_setBuilder_whenTheGivenBuilderIsNull() {

    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {

      //method
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

  //method
  @Test
  void testCase_setBuilder_whenHasAlreadyBuilder() {

    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {

      //method
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
