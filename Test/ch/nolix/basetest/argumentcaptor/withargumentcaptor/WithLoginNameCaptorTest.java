/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithLoginNameCaptorTest extends StandardTest {
  @Test
  void testCase_getLoginName_whenDoesNotHaveLoginName() {
    // setup
    final var testUnit = new WithLoginNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getLoginName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withLoginName_whenHasSuccessor() {
    // define test parameters
    final var loginName = "my_login_name";

    // setup
    final var andNameCaptor = new WithNameCaptor<>();
    final var testUnit = new WithLoginNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withLoginName(loginName);

    // verify
    expect(testUnit.getLoginName()).isEqualTo(loginName);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withLoginName_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithLoginNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withLoginName("my_login_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
