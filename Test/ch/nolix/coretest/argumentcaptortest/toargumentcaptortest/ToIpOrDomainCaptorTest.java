//package declaration
package ch.nolix.coretest.argumentcaptortest.toargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ToIpOrDomainCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveAIpOrDomain() {

    //setup
    final var testUnit = new ToIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(testUnit::getIpOrDomain).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_toIpOrDomain_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new ToIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.toIpOrDomain("nolix.ch")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_toIpOrDomain_whenHasNext() {

    //parameter definition
    final var domain = "nolix.ch";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.toIpOrDomain(domain);

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_toLocalAddress_whenHasNext() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.toLocalAddress();

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }
}
