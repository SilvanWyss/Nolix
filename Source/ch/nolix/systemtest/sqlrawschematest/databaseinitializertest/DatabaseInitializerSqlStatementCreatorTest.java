//package declaration
package ch.nolix.systemtest.sqlrawschematest.databaseinitializertest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.sqlrawschema.databaseinitializer.DatabaseInitializerSqlStatementCreator;
import ch.nolix.system.time.moment.Time;

//class
public final class DatabaseInitializerSqlStatementCreatorTest extends Test {

  //method
  @TestCase
  public void testCase_createSqlStatementToCreateSchemaTimestampEntry() {

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
