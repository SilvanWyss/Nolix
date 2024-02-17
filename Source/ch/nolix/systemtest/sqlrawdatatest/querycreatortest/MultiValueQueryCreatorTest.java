//package declaration
package ch.nolix.systemtest.sqlrawdatatest.querycreatortest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.sqlrawdata.querycreator.MultiValueQueryCreator;

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
    + "FROM TMultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiValueColumnId = 'my_column_id';";
    expect(result).isEqualTo(expectedResult);
  }
}
