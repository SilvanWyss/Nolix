//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.MultiValueQueryCreator;

//class
public final class MultiValueQueryCreatorTest extends Test {

  @TestCase
  public void testCase_createQueryToLoadMultiValueEntries() {

    //setup
    final var testUnit = new MultiValueQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadMultiValueEntries("my_entity_id", "my_column_id");

    //verification
    final var expectedResult = "SELECT Value "
    + "FROM MMultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiValueColumnId = 'my_column_id';";
    expect(result).isEqualTo(expectedResult);
  }
}
