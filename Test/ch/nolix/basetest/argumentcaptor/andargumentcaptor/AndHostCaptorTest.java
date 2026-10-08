/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndHostCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;

/**
 * @author Silvan Wyss
 */
final class AndHostCaptorTest extends StandardTest {
  @Test
  void testCase_andHost_whenHasNextArgumentCaptor() {
    // define test parameters
    final var host = "nolix.ch";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andHost(host);

    // verify
    expect(testUnit.getHost()).isEqualTo(host);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andHost_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndHostCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andHost("nolix.ch"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_andLocalHost_whenHasNextArgumentCaptor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndHostCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andLocalHost();

    // verify
    expect(testUnit.getHost()).isEqualTo(IPv4Catalog.LOOP_BACK_ADDRESS);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getHost_whenDoesNotHaveHost() {
    // setup
    final var testUnit = new AndHostCaptor<>();

    // execute & verify
    expectRunning(testUnit::getHost).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
