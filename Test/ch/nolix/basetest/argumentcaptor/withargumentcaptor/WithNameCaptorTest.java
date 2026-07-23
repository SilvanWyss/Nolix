/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithNameCaptorTest extends StandardTest {
  @Test
  void testCase_getName_whenDoesNotHaveName() {
    // setup
    final var testUnit = new WithNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withName_whenHasNext() {
    // define test parameters
    final var name = "my_name";

    // setup
    final var andNameCaptor = new WithNameCaptor<>();
    final var testUnit = new WithNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withName(name);

    // verify
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withName_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new WithNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withName("my_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
