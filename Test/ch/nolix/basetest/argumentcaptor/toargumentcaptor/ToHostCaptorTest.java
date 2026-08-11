/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.toargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToHostCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ToHostCaptorTest extends StandardTest {
  @Test
  void testCase_getHost_whenDoesNotHaveHost() {
    // setup
    final var testUnit = new ToHostCaptor<>();

    // execute & verify
    expectRunning(testUnit::getHost).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toHost_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ToHostCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.toHost("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toHost_whenHasSuccessor() {
    // define test parameters
    final var domain = "nolix.ch";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.toHost(domain);

    // verify
    expect(testUnit.getHost()).isEqualTo(domain);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_toLocalHost_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.toLocalHost();

    // verify
    expect(testUnit.getHost()).isEqualTo("127.0.0.1");
    expect(result).is(andNameCaptor);
  }
}
