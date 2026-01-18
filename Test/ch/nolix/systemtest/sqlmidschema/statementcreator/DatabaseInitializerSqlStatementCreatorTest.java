/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmidschema.statementcreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmidschema.statementcreator.DatabaseInitializationStatementCreator;
import ch.nolix.system.time.moment.Time;

/**
 * @author Silvan Wyss
 */
final class DatabaseInitializerSqlStatementCreatorTest extends StandardTest {
  @Test
  void testCase_createSqlStatementToCreateSchemaTimestampEntry() {
    //setup
    final var time = Time.withYear(2025);
    final var testUnit = new DatabaseInitializationStatementCreator();

    //execution
    final var result = testUnit.createStatementToCreateSchemaTimestampEntry(time);

    //verification setup
    final var expectedResult = //
    "INSERT INTO DatabaseProperty (ValueKey, Value_) VALUES ('SchemaTimestamp', '2025-01-01-00-00-00-000-000');";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
