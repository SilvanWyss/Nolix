package ch.nolix.coretest.argumentcaptortest.basetest;

import java.util.function.Supplier;

//JUnit import
import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ArgumentCaptorTest extends StandardTest {

  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNull() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(null) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_constructor_whenTheGivenNextArgumentCaptorIsNotValid() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>(new VoidObject()) {
    }) //
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_defaultConstructor() {

    //execution & verification
    expectRunning(() -> new ArgumentCaptor<String, VoidObject>() {
    }) //
      .doesNotThrowException();
  }

  @Test
  void testCase_nxtArgCpt_whenDoesNotHaveNextArgumentCaptor() {

    //setup
    final var testUnit = new ArgumentCaptor<String, VoidObject>() {
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
