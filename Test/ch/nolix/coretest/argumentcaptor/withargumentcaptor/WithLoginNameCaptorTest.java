/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class WithLoginNameCaptorTest extends StandardTest {
  @Test
  void testCase_getLoginName_whenDoesNotHaveLoginName() {
    //setup
    final var testUnit = new WithLoginNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getLoginName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withLoginName_whenHasNext() {
    //parameter definition
    final var loginName = "my_login_name";

    //setup
    final var andNameCaptor = new WithNameCaptor<>();
    final var testUnit = new WithLoginNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.withLoginName(loginName);

    //verification
    expect(testUnit.getLoginName()).isEqualTo(loginName);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withLoginName_whenDoesNotHaveNext() {
    //setup
    final var testUnit = new WithLoginNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withLoginName("my_login_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
