/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.midschema.fieldproperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.database.databaseproperty.Cardinality;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
final class FieldTypeTest extends StandardTest {
  @MethodSource
  private static ImmutableList<Arguments> getFieldTypesAndTheirCardinality() {
    return //
    ImmutableList.withElements(
      Arguments.of(FieldType.VALUE_FIELD, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_VALUE_FIELD, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_VALUE_FIELD, Cardinality.TO_MANY),
      Arguments.of(FieldType.REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_REFERENCE, Cardinality.TO_MANY),
      Arguments.of(FieldType.BACK_REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_BACK_REFERENCE, Cardinality.TO_MANY));
  }

  @ParameterizedTest
  @MethodSource("getFieldTypesAndTheirCardinality")
  void testCase_getCardinality(final FieldType testUnit, final Cardinality expectedCardinality) {
    // execute
    final var result = testUnit.getCardinality();

    // verify
    expect(result).is(expectedCardinality);
  }
}
