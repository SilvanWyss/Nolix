/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.toargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ToDatabaseNameCaptorTest extends StandardTest {
  @Test
  void testCase_getDatabaseName_whenDoesNotHaveDatabaseName() {
    // setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getDatabaseName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.toDatabase("my_database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toDatabaseName_whenHasSuccessor() {
    // define test parameters
    final var databaseName = "my_database";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToDatabaseNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.toDatabase(databaseName);

    // verify
    expect(testUnit.getDatabaseName()).isEqualTo(databaseName);
    expect(result).is(andNameCaptor);
  }
}
