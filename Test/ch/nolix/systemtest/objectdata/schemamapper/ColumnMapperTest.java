/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.schemamapper;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;

/**
 * @author Silvan Wyss
 */
final class ColumnMapperTest extends StandardTest {
  @Test
  void testCase_whenTheGivenFieldIsNull() {
    // setup
    final var testUnit = new ColumnMapper();

     // execute & verify
    expectRunning(() -> testUnit.mapFieldToColumn(null, "column_id", ImmutableList.createEmpty()))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }
}
