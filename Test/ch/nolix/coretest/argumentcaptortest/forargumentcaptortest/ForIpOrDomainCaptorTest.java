//package declaration
package ch.nolix.coretest.argumentcaptortest.forargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForIpOrDomainCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ForIpOrDomainCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_defaultConstructor() {
  
    //setup
    final var testUnit = new ForIpOrDomainCaptor<>();
  
    //execution & verification
    expectRunning(() -> testUnit.forIpOrDomain("nolix.tech")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_forIpOrDomain() {

    //parameter definition
    final var domain = "nolix.tech";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forIpOrDomain(domain);

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_forLocalAddress() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forLocalAddress();

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }
}
