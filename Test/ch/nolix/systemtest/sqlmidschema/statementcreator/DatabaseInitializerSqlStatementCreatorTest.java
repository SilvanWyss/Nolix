/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmidschema.statementcreator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmidschema.statementcreator.DatabaseInitializationStatementCreator;
import ch.nolix.system.time.main.Time;

/**
 * @author Silvan Wyss
 */
final class DatabaseInitializerSqlStatementCreatorTest extends StandardTest {
  @Test
  void testCase_createSqlStatementToCreateSchemaTimestampEntry() {
    // setup
    final var time = Time.withYear(2025);
    final var testUnit = new DatabaseInitializationStatementCreator();

    // execute
    final var result = testUnit.createStatementToCreateSchemaTimestampEntry(time);

    // verify setup
    final var expectedResult = //
    "INSERT INTO DatabaseProperty (Key_, Value_) VALUES ('SchemaTimestamp', '2025-01-01-00-00-00-000-000');";

    // verify
    expect(result).isEqualTo(expectedResult);
  }
}
