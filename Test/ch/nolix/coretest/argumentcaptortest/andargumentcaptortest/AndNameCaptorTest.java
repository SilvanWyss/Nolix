//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndNameCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andName_whenHasNext() {

    //parameter definition
    final var name = "my_name";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andName(name);

    //verification
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andName_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andName("my_name")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getName_whenDoesNotHaveAName() {

    //setup
    final var testUnit = new AndNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
