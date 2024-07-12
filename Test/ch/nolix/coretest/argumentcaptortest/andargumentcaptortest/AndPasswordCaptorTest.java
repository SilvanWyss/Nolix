//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndPasswordCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andPassword_whenHasNext() {

    //parameter definition
    final var password = "my_password";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPasswordCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andPassword(password);

    //verification
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andPassword_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andPassword("my_password")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getPassword_whenDoesNotHaveAPassword() {

    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
