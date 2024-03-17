//package declaration
package ch.nolix.systemtest.sqlrawdatatest.querycreatortest;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.sqlrawdata.querycreator.MultiReferenceQueryCreator;

//class
public final class MultiReferenceQueryCreatorTest extends StandardTest {

  @TestCase
  public void testCase_createQueryToLoadMultiReferenceEntries() {

    //setup
    final var testUnit = new MultiReferenceQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadMultiReferenceEntries("my_entity_id", "my_column_id");

    //verification
    final var expectedResult = "SELECT ReferencedEntityId "
    + "FROM TMultiReferenceEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiReferenceColumnId = 'my_column_id';";
    expect(result).isEqualTo(expectedResult);
  }
}
