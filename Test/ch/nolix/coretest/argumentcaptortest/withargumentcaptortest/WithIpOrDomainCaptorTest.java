//package declaration
package ch.nolix.coretest.argumentcaptortest.withargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithIpOrDomainCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class WithIpOrDomainCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveIpOrDomain() {

    //setup
    final var testUnit = new WithIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(testUnit::getIpOrDomain).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_withIpOrDomain_whenHasNext() {

    //parameter definition
    final var domain = "nolix.ch";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.withIpOrDomain(domain);

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_withIpOrDomain_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new WithIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withIpOrDomain("nolix.ch")).throwsException().ofType(InvalidArgumentException.class);
  }
}
