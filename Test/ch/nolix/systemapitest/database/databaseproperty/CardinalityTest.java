/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.database.databaseproperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseproperty.BaseCardinality;
import ch.nolix.systemapi.database.databaseproperty.Cardinality;

/**
 * @author Silvan Wyss
 */
final class CardinalityTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getCardinalitysAndTheirBaseCardinality() {
    return //
    ImmutableList.withElements(
      Arguments.of(Cardinality.TO_ONE, BaseCardinality.SINGLE),
      Arguments.of(Cardinality.TO_ONE_OR_NONE, BaseCardinality.SINGLE),
      Arguments.of(Cardinality.TO_MANY, BaseCardinality.MULTI));
  }

  @ParameterizedTest
  @MethodSource("getCardinalitysAndTheirBaseCardinality")
  void testCase_fromSpecification(final Cardinality testUnit, final BaseCardinality expectedBaseCardinality) {
    // execute
    final var result = testUnit.getBaseCardinality();

    // verify
    expect(result).is(expectedBaseCardinality);
  }
}
