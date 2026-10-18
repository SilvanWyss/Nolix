/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.withargumentcaptor.WithPasswordCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithPasswordCaptorTest extends StandardTest {
  @Test
  void testCase_getPassword_whenDoesNotHavePassword() {
    // setup
    final var testUnit = new WithPasswordCaptor<>();

    // execute & verify
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withPassword_whenHasSuccessor() {
    // define test parameters
    final var password = "secret";

    // setup
    final var successor = new WithPasswordCaptor<>();
    final var testUnit = new WithPasswordCaptor<>(successor);

    // execute
    final var result = testUnit.withPassword(password);

    // verify
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(successor);
  }

  @Test
  void testCase_withPassword_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithPasswordCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withPassword("secret"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
