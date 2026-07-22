/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndLoginNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndLoginNameCaptorTest extends StandardTest {
  @Test
  void testCase_andLoginName_whenHasNext() {
    // parameter definition
    final var loginName = "my_login_name";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndLoginNameCaptor<>(andNameCaptor);

   // execute
    final var result = testUnit.andLoginName(loginName);

   // verify
    expect(testUnit.getLoginName()).isEqualTo(loginName);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andLoginName_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new AndLoginNameCaptor<>();

   // execute & verification
    expectRunning(() -> testUnit.andLoginName("my_login_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getLoginName_whenDoesNotHaveLoginName() {
    // setup
    final var testUnit = new AndLoginNameCaptor<>();

   // execute & verification
    expectRunning(testUnit::getLoginName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
