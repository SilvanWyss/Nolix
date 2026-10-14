/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndSqlDatabaseEngineCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
final class AndSqlDatabaseEngineCaptorTest extends StandardTest {
  @Test
  void testCase_andSqlDatabaseEngine_whenHasSuccessor() {
    // define test parameters
    final var sqlDatabaseEngine = SqlDatabaseEngine.MS_SQL;

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndSqlDatabaseEngineCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andSqlDatabaseEngine(sqlDatabaseEngine);

    // verify
    expect(testUnit.getSqlDatabaseEngine()).is(sqlDatabaseEngine);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andSqlDatabaseEngine_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndSqlDatabaseEngineCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andSqlDatabaseEngine(SqlDatabaseEngine.MS_SQL))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getSqlDatabaseEngine_whenDoesNotHaveSqlDatabaseEngine() {
    // setup
    final var testUnit = new AndSqlDatabaseEngineCaptor<>();

    // execute & verify
    expectRunning(testUnit::getSqlDatabaseEngine)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
