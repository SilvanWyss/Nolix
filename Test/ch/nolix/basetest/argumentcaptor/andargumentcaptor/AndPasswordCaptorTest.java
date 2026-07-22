/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndPasswordCaptorTest extends StandardTest {
  @Test
  void testCase_andPassword_whenHasNext() {
    // parameter definition
    final var password = "my_password";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPasswordCaptor<>(andNameCaptor);

   // execute
    final var result = testUnit.andPassword(password);

   // verify
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andPassword_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new AndPasswordCaptor<>();

   // execute & verification
    expectRunning(() -> testUnit.andPassword("my_password"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getPassword_whenDoesNotHavePassword() {
    // setup
    final var testUnit = new AndPasswordCaptor<>();

   // execute & verification
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
