/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithIpOrDomainCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
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
    expectRunning(() -> testUnit.withIpOrDomain("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
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
