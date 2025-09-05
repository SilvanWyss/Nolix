package ch.nolix.systemtest.sqlmiddata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.querycreator.MultiValueQueryCreator;

final class MultiValueQueryCreatorTest extends StandardTest {
  @Test
  void testCase_createQueryToLoadMultiValueEntries() {
    //setup
    final var testUnit = new MultiValueQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadMultiValueEntries("my_entity_id", "my_column_id");

    //verification setup
    final var expectedResult = //
    "SELECT Value_ "
    + "FROM MultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiValueColumnId = 'my_column_id';";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
