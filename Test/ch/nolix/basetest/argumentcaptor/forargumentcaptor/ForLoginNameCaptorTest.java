/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForLoginNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForLoginNameCaptorTest extends StandardTest {
  @Test
  void testCase_forLoginName_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForLoginNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forLoginName("login_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forLoginName_whenHasSuccessor() {
    // define test parameters
    final var loginName = "login_name";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForLoginNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forLoginName(loginName);

    // verify
    expect(testUnit.getLoginName()).isEqualTo(loginName);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getLoginName_whenDoesNotHaveLoginName() {
    // setup
    final var testUnit = new ForLoginNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getLoginName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
