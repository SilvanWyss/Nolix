/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmiddata.sqlmapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.sql.sqltool.SqlLiteralMapper;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SqlLiteralMapperTest extends StandardTest {
  @Test
  void testCase_whenTheGivenNullableValueStringIsNull() {
    // setup
    final var testUnit = new SqlLiteralMapper();

   // execute
    final var result = testUnit.mapNullableValueStringToSqlLiteral(null);

   // verify
    expect(result).isEqualTo("NULL");
  }

  @ParameterizedTest
  @CsvSource({
  "'', '\'''\''",
  "A, '\''A'\''",
  "a, '\''a'\''",
  "text, '\''text'\''"
  })
  void testCase_whenTheGivenNullableValueIsNotNull(final String nullableValue, final String expectedResult) {
    // setup
    final var testUnit = new SqlLiteralMapper();

   // execute
    final var result = testUnit.mapNullableValueStringToSqlLiteral(nullableValue);

   // verify
    expect(result).isEqualTo(expectedResult);
  }
}
