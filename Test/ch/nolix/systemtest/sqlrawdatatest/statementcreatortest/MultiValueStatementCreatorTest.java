package ch.nolix.systemtest.sqlrawdatatest.statementcreatortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlrawdata.statementcreator.MultiValueStatementCreator;

final class MultiValueStatementCreatorTest extends StandardTest {

  @Test
  void testCase_createStatementToDeleteMultiValueEntries() {

    //setup
    final var entityId = "my_entity_id";
    final var multiValueColumnId = "my_multi_value_column_id";
    final var testUnit = new MultiValueStatementCreator();

    //verification setup
    final var expectedResult = //
    "DELETE FROM TMultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' AND MultiValueColumnId = 'my_multi_value_column_id';";

    //execution
    final var result = testUnit.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToDeleteMultiValueEntry() {

    //setup
    final var entityId = "my_entity_id";
    final var multiValueColumnId = "my_multi_value_column_id";
    final var entry = "my_entry";
    final var testUnit = new MultiValueStatementCreator();

    //verification setup
    final var expectedResult = //
    "DELETE FROM TMultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' AND MultiValueColumnId = 'my_multi_value_column_id' AND Value = 'my_entry';";

    //execution
    final var result = testUnit.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToInsertMultiValueEntry() {

    //setup
    final var entityId = "my_entity_id";
    final var multiValueColumnId = "my_multi_value_column_id";
    final var entry = "my_entry";
    final var testUnit = new MultiValueStatementCreator();

    //verification setup
    final var expectedResult = //
    "INSERT INTO TMultiValueEntry "
    + "(EntityId, MultiValueColumnId, Value) "
    + "VALUES ('my_entity_id', 'my_multi_value_column_id', 'my_entry');";

    //execution
    final var result = testUnit.createStatementToInsertMultiValueEntry(entityId, multiValueColumnId, entry);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
