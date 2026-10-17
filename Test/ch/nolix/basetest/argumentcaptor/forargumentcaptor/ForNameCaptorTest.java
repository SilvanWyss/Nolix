/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForNameCaptorTest extends StandardTest {
  @Test
  void testCase_forName_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forName("name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forName_whenHasSuccessor() {
    // define test parameters
    final var name = "name";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forName(name);

    // verify
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getName_whenDoesNotHaveName() {
    // setup
    final var testUnit = new ForNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
