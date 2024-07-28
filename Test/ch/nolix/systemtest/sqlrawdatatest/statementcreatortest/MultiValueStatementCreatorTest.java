//package declaration
package ch.nolix.systemtest.sqlrawdatatest.statementcreatortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlrawdata.statementcreator.MultiValueStatementCreator;

//class
final class MultiValueStatementCreatorTest extends StandardTest {

  //method
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
