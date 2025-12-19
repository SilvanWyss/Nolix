package ch.nolix.coretest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForIpOrDomainCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ForIpOrDomainCaptorTest extends StandardTest {
  @Test
  void testCase_forIpOrDomain_whenDoesNotHaveNext() {
    //setup
    final var testUnit = new ForIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.forIpOrDomain("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forIpOrDomain_whenHasNext() {
    //parameter definition
    final var domain = "nolix.ch";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forIpOrDomain(domain);

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forLocalAddress_whenHasNext() {
    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forLocalAddress();

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveIpOrDomain() {
    //setup
    final var testUnit = new ForIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(testUnit::getIpOrDomain).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
