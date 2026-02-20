/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class AndPasswordCaptorTest extends StandardTest {
  @Test
  void testCase_andPassword_whenHasNext() {
    //parameter definition
    final var password = "my_password";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPasswordCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andPassword(password);

    //verification
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andPassword_whenDoesNotHaveNext() {
    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andPassword("my_password"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getPassword_whenDoesNotHavePassword() {
    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
