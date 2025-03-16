package ch.nolix.system.sqlrawdata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.querycreator.MultiReferenceQueryCreator;

final class MultiReferenceQueryCreatorTest extends StandardTest {

  @Test
  void testCase_createQueryToLoadMultiReferenceEntries() {

    //setup
    final var testUnit = new MultiReferenceQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadMultiReferenceEntries("my_entity_id", "my_column_id");

    //verification setup
    final var expectedResult = //
    "SELECT ReferencedEntityId "
    + "FROM MultiReferenceEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiReferenceColumnId = 'my_column_id';";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
