package ch.nolix.systemtest.sqlrawschematest.databaseinitializertest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlrawschema.databaseinitializer.DatabaseInitializerSqlStatementCreator;
import ch.nolix.system.time.moment.Time;

final class DatabaseInitializerSqlStatementCreatorTest extends StandardTest {

  @Test
  void testCase_createSqlStatementToCreateSchemaTimestampEntry() {

    //setup
    final var time = Time.withYear(2025);
    final var testUnit = new DatabaseInitializerSqlStatementCreator();

    //execution
    final var result = testUnit.createSqlStatementToCreateSchemaTimestampEntry(time);

    //verification
    final var expectedResult = //
    "INSERT INTO MDatabaseProperty (ValueKey, Value) VALUES ('SchemaTimestamp', '2025-01-01-00-00-00-000');";
    expect(result).isEqualTo(expectedResult);
  }
}
