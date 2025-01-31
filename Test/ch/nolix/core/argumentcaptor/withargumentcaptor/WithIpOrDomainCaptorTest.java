package ch.nolix.core.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class WithIpOrDomainCaptorTest extends StandardTest {

  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveIpOrDomain() {

    //setup
    final var testUnit = new WithIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(testUnit::getIpOrDomain).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

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

  @Test
  void testCase_withIpOrDomain_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new WithIpOrDomainCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withIpOrDomain("nolix.ch")).throwsException().ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_withLocalAddress_whenHasNext() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithIpOrDomainCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.withLocalAddress();

    //verification
    expect(testUnit.getIpOrDomain()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }
}
