package ch.nolix.coretest.sqltest.sqltooltest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;

final class SqlCollectorTest extends StandardTest {

  @Test
  void testCase_addSqlStatement() {

    //setup
    final var sqlStatement1 = "CREATE TABLE Person (Name nvarchar(100),WeightInKilogram Float);";
    final var sqlStatement2 = "CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);";
    final var testUnit = new SqlCollector();

    //execution
    testUnit.addSqlStatement(sqlStatement1);
    testUnit.addSqlStatement(sqlStatement2);

    //verification
    final var actualSqlStatements = testUnit.getSqlStatements();
    expect(actualSqlStatements).containsExactlyInSameOrder(sqlStatement1, sqlStatement2);
  }

  @Test
  void testCase_addSqlStatement_whenTheGivenSqlStatementIsNull() {

    //setup
    final String sqlStatement = null;
    final var testUnit = new SqlCollector();

    //execution & verification
    expectRunning(() -> testUnit.addSqlStatement(sqlStatement))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given SQL statement is null.");
  }

  @ParameterizedTest
  @ValueSource(strings = { StringCatalogue.EMPTY_STRING, StringCatalogue.SPACE, StringCatalogue.TABULATOR })
  void testCase_addSqlStatement_whenTheGivenSqlStatementIsBlank(final String sqlStatement) {

    //setup
    final var testUnit = new SqlCollector();

    //execution & verification
    expectRunning(() -> testUnit.addSqlStatement(sqlStatement))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given SQL statement is blank.");
  }

  @Test
  void testCase_addSqlStatements() {

    //setup
    final var sqlStatement1 = "CREATE TABLE Person (Name nvarchar(100),WeightInKilogram Float);";
    final var sqlStatement2 = "CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);";
    final var sqlStatements = ImmutableList.withElement(sqlStatement1, sqlStatement2);
    final var testUnit = new SqlCollector();

    //execution
    testUnit.addSqlStatements(sqlStatements);

    //verification
    final var actualSqlStatements = testUnit.getSqlStatements();
    expect(actualSqlStatements).containsExactlyInSameOrder(sqlStatement1, sqlStatement2);
  }

  @Test
  void testCase_constructor() {

    //execution
    final var result = new SqlCollector();

    //verification
    expect(result.isEmpty());
    expect(result.getSqlStatements()).isEmpty();
  }

  @Test
  void testCase_executeAndClearUsingConnection() {

    //setup
    final var sqlConnectionMock = Mockito.mock(ISqlConnection.class);
    final var testUnit = new SqlCollector();
    testUnit.addSqlStatement("CREATE TABLE Person (Name nvarchar(100),WeightInKilogram Float);");
    testUnit.addSqlStatement("CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);");

    //execution
    testUnit.executeAndClearUsingConnection(sqlConnectionMock);

    //verification
    expect(testUnit.isEmpty());
    Mockito.verify(sqlConnectionMock).executeStatements(testUnit.getSqlStatements());
  }
}
