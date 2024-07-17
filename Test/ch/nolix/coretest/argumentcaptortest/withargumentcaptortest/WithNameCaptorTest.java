//package declaration
package ch.nolix.coretest.argumentcaptortest.withargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class WithNameCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_getName_whenDoesNotHaveName() {

    //setup
    final var testUnit = new WithNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_withName_whenHasNext() {

    //parameter definition
    final var name = "my_name";

    //setup
    final var andNameCaptor = new WithNameCaptor<>();
    final var testUnit = new WithNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.withName(name);

    //verification
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_withName_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new WithNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withName("my_name"))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }
}
