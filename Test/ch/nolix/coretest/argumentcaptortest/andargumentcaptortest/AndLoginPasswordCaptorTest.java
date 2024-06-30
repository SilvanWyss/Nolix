//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndLoginPasswordCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andLoginPassword() {

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
}
