//package declaration
package ch.nolix.coretest.argumentcaptortest.basetest;

//JUnit import
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ArgumentCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNull() {

    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(null) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNotValid() {

    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(new VoidObject()) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_defaultConstructor() {

    expectRunning(() -> new ArgumentCaptor<String, VoidObject>() {
    }) //
      .doesNotThrowException();
  }

  @Test
  void testCase_nxtArgCpt_whenDoesNotHaveANextArgumentCaptor() {

    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {
    };

    expectRunning(testUnit::nxtArgCpt)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
