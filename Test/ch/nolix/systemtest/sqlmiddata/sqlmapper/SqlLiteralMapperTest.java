package ch.nolix.systemtest.sqlmiddata.sqlmapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.sqlmapper.SqlLiteralMapper;

final class SqlLiteralMapperTest extends StandardTest {
  @Test
  void testCase_whenTheGivenNullableValueStringIsNull() {
    //setup
    final var testUnit = new SqlLiteralMapper();

    //execution
    final var result = testUnit.mapNullableValueStringToSqlLiteral(null);

    //verification
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
    //setup
    final var testUnit = new SqlLiteralMapper();

    //execution
    final var result = testUnit.mapNullableValueStringToSqlLiteral(nullableValue);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
