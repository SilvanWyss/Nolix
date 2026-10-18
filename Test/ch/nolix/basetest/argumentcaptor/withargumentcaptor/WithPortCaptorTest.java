/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithPortCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithPortCaptorTest extends StandardTest {
  @Test
  void testCase_getPort_whenDoesNotHavePort() {
    // setup
    final var testUnit = new WithPortCaptor<>();

    // execute & verify
    expectRunning(testUnit::getPort).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withHttpPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withHttpPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(80);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withHttpsPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withHttpsPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(443);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withMsSqlPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withMsSqlPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(1433);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withPort_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithPortCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withPort(8080))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withPort_whenHasSuccessor() {
    // define test parameters
    final var port = 8080;

    // setup
    final var andPortCaptor = new AndPortCaptor<>();
    final var testUnit = new WithPortCaptor<>(andPortCaptor);

    // execute
    final var result = testUnit.withPort(port);

    // verify
    expect(testUnit.getPort()).isEqualTo(port);
    expect(result).is(andPortCaptor);
  }
}
