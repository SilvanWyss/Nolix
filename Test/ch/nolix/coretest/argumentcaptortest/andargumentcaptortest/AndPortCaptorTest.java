//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.testing.test.StandardTest;

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
}
