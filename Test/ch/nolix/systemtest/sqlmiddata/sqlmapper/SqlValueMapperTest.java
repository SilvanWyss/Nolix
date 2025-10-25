package ch.nolix.systemtest.sqlmiddata.sqlmapper;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.sqlmapper.SqlValueMapper;

final class SqlValueMapperTest extends StandardTest {
  @Test
  void testCase_whenTheGivenNullableValueIsAText() {
    //setup
    final var testUnit = new SqlValueMapper();

    //execution
    final var result = testUnit.mapNullableValueStringToSqlValue("text");

    //verification
    expect(result).isEqualTo("'text'");
  }

  @Test
  void testCase_whenTheGivenNullableValueIsEmpty() {
    //setup
    final var testUnit = new SqlValueMapper();

    //execution
    final var result = testUnit.mapNullableValueStringToSqlValue("");

    //verification
    expect(result).isEqualTo("''");
  }

  @Test
  void testCase_whenTheGivenNullableValueStringIsNull() {
    //setup
    final var testUnit = new SqlValueMapper();

    //execution
    final var result = testUnit.mapNullableValueStringToSqlValue(null);

    //verification
    expect(result).isEqualTo("NULL");
  }
}
