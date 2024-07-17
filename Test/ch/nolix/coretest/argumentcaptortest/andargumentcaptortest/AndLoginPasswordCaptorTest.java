//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndLoginPasswordCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andLoginPassword_whenHasNext() {

    //parameter definition
    final var loginPassword = "my_login_password";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndLoginPasswordCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andLoginPassword(loginPassword);

    //verification
    expect(testUnit.getLoginPassword()).isEqualTo(loginPassword);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andLoginPassword_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndLoginPasswordCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andLoginPassword("my_login_password"))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getLoginPassword_whenDoesNotHaveLoginPassword() {

    //setup
    final var testUnit = new AndLoginPasswordCaptor<>();

    //execution & verification
    expectRunning(testUnit::getLoginPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
