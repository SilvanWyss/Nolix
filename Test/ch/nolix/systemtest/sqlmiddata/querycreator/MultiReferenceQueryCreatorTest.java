/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmiddata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.querycreator.MultiReferenceQueryCreator;

/**
 * @author Silvan Wyss
 */
final class MultiReferenceQueryCreatorTest extends StandardTest {
  @Test
  void testCase_createQueryToLoadMultiReferenceEntries() {
    //setup
    final var testUnit = new MultiReferenceQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadMultiReferenceEntries("my_entity_id", "my_column_id");

    //verification setup
    final var expectedResult = //
    "SELECT EntityId, EntityTableId, MultiReferenceColumnId, ReferencedEntityId, ReferencedEntityTableId "
    + "FROM MultiReferenceEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiReferenceColumnId = 'my_column_id';";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
