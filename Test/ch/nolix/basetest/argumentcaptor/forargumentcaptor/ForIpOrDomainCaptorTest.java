/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForHostCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForIpOrDomainCaptorTest extends StandardTest {
  @Test
  void testCase_forIpOrDomain_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new ForHostCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forHost("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forIpOrDomain_whenHasNext() {
    // define test parameters
    final var domain = "nolix.ch";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forHost(domain);

    // verify
    expect(testUnit.getHost()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forLocalAddress_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forLocalHost();

    // verify
    expect(testUnit.getHost()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getIpOrDomain_whenDoesNotHaveIpOrDomain() {
    // setup
    final var testUnit = new ForHostCaptor<>();

    // execute & verify
    expectRunning(testUnit::getHost).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
