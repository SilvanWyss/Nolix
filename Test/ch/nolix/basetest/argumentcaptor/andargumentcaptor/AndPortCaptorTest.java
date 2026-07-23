/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndPortCaptorTest extends StandardTest {
  @Test
  void testCase_andPort_whenHasNext() {
    // define test parameters
    final var port = 8000;

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andPort(port);

    // verify
    expect(testUnit.getPort()).isEqualTo(port);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andPort_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new AndPortCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andPort(8000))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_andHttpPort_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andHttpPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(80);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andHttpsPort_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andHttpsPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(443);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andMsSqlPort_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andMsSqlPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(1433);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getPort_whenDoesNotHavePort() {
    // setup
    final var testUnit = new AndPortCaptor<>();

    // execute & verify
    expectRunning(testUnit::getPort).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
