//package declaration
package ch.nolix.coretest.errorcontroltest.exceptiontest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class WrapperExceptionTest extends StandardTest {

  //method
  @Test
  void testCase_forError() {

    //setup
    final var exception = new Exception();

    //execution
    final var result = WrapperException.forError(exception);

    //verification
    expect(result.getCause()).is(exception);
  }

  //method
  @Test
  void testCase_forErrorMessageAndError() {

    //setup
    final var errorMessage = "An Exception arrised.";
    final var exception = new Exception();

    //execution
    final var result = WrapperException.forErrorMessageAndError(errorMessage, exception);

    //verification
    expect(result.getMessage()).is(errorMessage);
    expect(result.getCause()).is(exception);
  }
}
