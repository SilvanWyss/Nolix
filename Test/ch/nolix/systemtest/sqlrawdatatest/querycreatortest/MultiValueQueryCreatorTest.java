//package declaration
package ch.nolix.systemtest.sqlrawdatatest.querycreatortest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlrawdata.querycreator.MultiValueQueryCreator;

//class
final class MultiValueQueryCreatorTest extends StandardTest {

  @Test
  void testCase_createQueryToLoadMultiValueEntries() {

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
