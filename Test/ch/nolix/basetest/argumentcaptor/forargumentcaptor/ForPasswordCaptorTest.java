/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForPasswordCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForPasswordCaptorTest extends StandardTest {
  @Test
  void testCase_forPassword_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForPasswordCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forPassword("password"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forPassword_whenHasSuccessor() {
    // define test parameters
    final var password = "password";

    // setup
    final var andPasswordCaptor = new AndPasswordCaptor<>();
    final var testUnit = new ForPasswordCaptor<>(andPasswordCaptor);

    // execute
    final var result = testUnit.forPassword(password);

    // verify
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(andPasswordCaptor);
  }

  @Test
  void testCase_getPassword_whenDoesNotHavePassword() {
    // setup
    final var testUnit = new ForPasswordCaptor<>();

    // execute & verify
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
