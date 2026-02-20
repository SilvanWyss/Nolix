/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithSqlDatabaseEngineCaptor;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
final class WithSqlDatabaseEngineCaptorTest extends StandardTest {
  @Test
  void testCase_getSqlDatabaseEngine_whenDoesNotHaveSqlDatabaseEngine() {
    //setup
    final var testUnit = new WithSqlDatabaseEngineCaptor<>();

    //execution & verification
    expectRunning(testUnit::getSqlDatabaseEngine).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withSqlDatabaseEngine_whenHasNext() {
    //parameter definition
    final var sqlDatabaseEngine = SqlDatabaseEngine.MSSQL;

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithSqlDatabaseEngineCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.withSqlDatabaseEngine(sqlDatabaseEngine);

    //verification
    expect(testUnit.getSqlDatabaseEngine()).isEqualTo(sqlDatabaseEngine);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withSqlDatabaseEngine_whenDoesNotHaveNext() {
    //setup
    final var testUnit = new WithSqlDatabaseEngineCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
