/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForPortCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForPortCaptorTest extends StandardTest {
  @Test
  void testCase_forHttpPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forHttpPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(80);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forHttpsPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forHttpsPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(443);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forMsSqlPort_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForPortCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forMsSqlPort();

    // verify
    expect(testUnit.getPort()).isEqualTo(1433);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forPort_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForPortCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forPort(8080))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forPort_whenHasSuccessor() {
    // define test parameters
    final var port = 8080;

    // setup
    final var andPortCaptor = new AndPortCaptor<>();
    final var testUnit = new ForPortCaptor<>(andPortCaptor);

    // execute
    final var result = testUnit.forPort(port);

    // verify
    expect(testUnit.getPort()).isEqualTo(port);
    expect(result).is(andPortCaptor);
  }

  @Test
  void testCase_getPort_whenDoesNotHavePort() {
    // setup
    final var testUnit = new ForPortCaptor<>();

    // execute & verify
    expectRunning(testUnit::getPort).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
