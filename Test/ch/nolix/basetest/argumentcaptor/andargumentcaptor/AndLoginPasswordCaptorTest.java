/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndLoginPasswordCaptorTest extends StandardTest {
  @Test
  void testCase_andLoginPassword_whenHasNext() {
    // define test parameters
    final var loginPassword = "my_login_password";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndLoginPasswordCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andLoginPassword(loginPassword);

    // verify
    expect(testUnit.getLoginPassword()).isEqualTo(loginPassword);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andLoginPassword_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new AndLoginPasswordCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andLoginPassword("my_login_password"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getLoginPassword_whenDoesNotHaveLoginPassword() {
    // setup
    final var testUnit = new AndLoginPasswordCaptor<>();

    // execute & verify
    expectRunning(testUnit::getLoginPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
