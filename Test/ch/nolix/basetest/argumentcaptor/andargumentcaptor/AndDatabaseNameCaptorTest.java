/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndDatabaseNameCaptorTest extends StandardTest {
  @Test
  void testCase_andDatabase_whenHasNext() {
    // parameter definition
    final var databaseName = "my_database";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndDatabaseNameCaptor<>(andNameCaptor);

   // execute
    final var result = testUnit.andDatabase(databaseName);

   // verify
    expect(testUnit.getDatabaseName()).isEqualTo(databaseName);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andDatabase_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new AndDatabaseNameCaptor<>();

   // execute & verification
    expectRunning(() -> testUnit.andDatabase("my_database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getDatabaseName_whenDoesNotHaveDatabaseName() {
    // setup
    final var testUnit = new AndDatabaseNameCaptor<>();

   // execute & verification
    expectRunning(testUnit::getDatabaseName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
