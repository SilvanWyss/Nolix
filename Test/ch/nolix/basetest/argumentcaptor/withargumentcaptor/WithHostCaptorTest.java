/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithHostCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithHostCaptorTest extends StandardTest {
  @Test
  void testCase_getHost_whenDoesNotHaveHost() {
    // setup
    final var testUnit = new WithHostCaptor<>();

    // execute & verify
    expectRunning(testUnit::getHost).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withHost_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithHostCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withHost("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withHost_whenHasSuccessor() {
    // define test parameters
    final var host = "nolix.ch";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withHost(host);

    // verify
    expect(testUnit.getHost()).isEqualTo(host);
    expect(result).is(andNameCaptor);
  }
}
