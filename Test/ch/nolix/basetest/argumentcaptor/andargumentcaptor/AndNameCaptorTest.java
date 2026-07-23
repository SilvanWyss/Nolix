/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndNameCaptorTest extends StandardTest {
  @Test
  void testCase_andName_whenHasSuccessor() {
    // define test parameters
    final var name = "my_name";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andName(name);

    // verify
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andName_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andName("my_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getName_whenDoesNotHaveName() {
    // setup
    final var testUnit = new AndNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
