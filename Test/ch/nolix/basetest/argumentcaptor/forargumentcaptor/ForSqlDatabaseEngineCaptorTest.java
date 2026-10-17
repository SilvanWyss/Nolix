/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.forargumentcaptor.ForNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForSqlDatabaseEngineCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
final class ForSqlDatabaseEngineCaptorTest extends StandardTest {
  @Test
  void testCase_forSqlDatabaseEngine_whenHasSuccessor() {
    // define test parameters
    final var sqlDatabaseEngine = SqlDatabaseEngine.MS_SQL;

    // setup
    final var forNameCaptor = new ForNameCaptor<>();
    final var testUnit = new ForSqlDatabaseEngineCaptor<>(forNameCaptor);

    // execute
    final var result = testUnit.forSqlDatabaseEngine(sqlDatabaseEngine);

    // verify
    expect(testUnit.getSqlDatabaseEngine()).is(sqlDatabaseEngine);
    expect(result).is(forNameCaptor);
  }

  @Test
  void testCase_forSqlDatabaseEngine_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForSqlDatabaseEngineCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forSqlDatabaseEngine(SqlDatabaseEngine.MS_SQL))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getSqlDatabaseEngine_whenDoesNotHaveSqlDatabaseEngine() {
    // setup
    final var testUnit = new ForSqlDatabaseEngineCaptor<>();

    // execute & verify
    expectRunning(testUnit::getSqlDatabaseEngine).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
