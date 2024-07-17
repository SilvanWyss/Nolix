//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndLoginNameCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andLoginName_whenHasNext() {

    //parameter definition
    final var loginName = "my_login_name";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndLoginNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andLoginName(loginName);

    //verification
    expect(testUnit.getLoginName()).isEqualTo(loginName);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andLoginName_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndLoginNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andLoginName("my_login_name"))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getLoginName_whenDoesNotHaveLoginName() {

    //setup
    final var testUnit = new AndLoginNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getLoginName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
