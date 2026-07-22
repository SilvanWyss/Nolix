/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.midschema.databasestructure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.midschema.databasestructure.EntityMetaField;

/**
 * @author Silvan Wyss
 */
final class EntityMetaFieldTest extends StandardTest {
  @MethodSource
  private static ImmutableList<Arguments> getFieldTypesAndTheirCardinality() {
    return //
    ImmutableList.withElements(
      Arguments.of(EntityMetaField.ID, "Id"),
      Arguments.of(EntityMetaField.SAVE_STAMP, "SaveStamp"),
      Arguments.of(EntityMetaField.VALID_FROM_DATE_TIME, "ValidFromDateTime"),
      Arguments.of(EntityMetaField.VALID_TO_DATE_TIME, "ValidToDateTime"));
  }

  @ParameterizedTest
  @MethodSource("getFieldTypesAndTheirCardinality")
  void testCase_toString(final EntityMetaField testUnit, final String expectedResult) {
   // execute
    final var result = testUnit.toString();

   // verify
    expect(result).isEqualTo(expectedResult);
  }
}
