/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmiddata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.querycreator.MultiValueQueryCreator;

/**
 * @author Silvan Wyss
 */
final class MultiValueQueryCreatorTest extends StandardTest {
  @Test
  void testCase_createQueryToLoadMultiValueEntries() {
    // setup
    final var testUnit = new MultiValueQueryCreator();

    // execute
    final var result = testUnit.createQueryToLoadMultiValueEntries("my_entity_id", "my_column_id");

    // verify setup
    final var expectedResult = //
    "SELECT Value_ "
    + "FROM MultiValueEntry "
    + "WHERE EntityId = 'my_entity_id' "
    + "AND MultiValueColumnId = 'my_column_id';";

    // verify
    expect(result).isEqualTo(expectedResult);
  }
}
