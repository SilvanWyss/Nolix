//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndPortCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andPort() {

    //parameter definition
    final var port = 8000;

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andPort(port);

    //verification
    expect(testUnit.getPort()).isEqualTo(port);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andHttpPort() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andHttpPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(80);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andHttpsPort() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andHttpsPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(443);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andMsSqlPort() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andMsSqlPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(1433);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_defaultConstructor() {

    //setup
    final var testUnit = new AndPortCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andPort(8000)).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getPort_whenDoesNotHaveAPort() {

    //setup
    final var testUnit = new AndPortCaptor<>();

    //execution & verification
    expectRunning(testUnit::getPort).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
