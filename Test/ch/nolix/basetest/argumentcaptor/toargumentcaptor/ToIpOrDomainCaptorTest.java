/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.toargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ToIpOrDomainCaptorTest extends StandardTest {
  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveIpOrDomain() {
    // setup
    final var testUnit = new ToIpOrDomainCaptor<>();

    // execution & verification
    expectRunning(testUnit::getHost).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toIpOrDomain_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new ToIpOrDomainCaptor<>();

    // execution & verification
    expectRunning(() -> testUnit.toHost("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toIpOrDomain_whenHasNext() {
    // parameter definition
    final var domain = "nolix.ch";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToIpOrDomainCaptor<>(andNameCaptor);

    // execution
    final var result = testUnit.toHost(domain);

    // verification
    expect(testUnit.getHost()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_toLocalAddress_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToIpOrDomainCaptor<>(andNameCaptor);

    // execution
    final var result = testUnit.toLocalHost();

    // verification
    expect(testUnit.getHost()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }
}
