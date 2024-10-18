package ch.nolix.coretest.argumentcaptortest.withargumentcaptortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithSqlDatabaseEngineCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

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
    expectRunning(() -> testUnit.withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)).throwsException()
      .ofType(InvalidArgumentException.class);
  }
}
