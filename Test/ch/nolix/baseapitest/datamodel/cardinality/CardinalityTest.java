/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.datamodel.cardinality;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.baseapi.datamodel.cardinality.Cardinality;

/**
 * @author Silvan Wyss
 */
final class CardinalityTest extends StandardTest {
  @MethodSource
  private static IWellOrderContainer<Arguments> getCardinalitysAndTheirBaseCardinality() {
    return //
    ImmutableList.withElements(
      Arguments.of(Cardinality.TO_ONE, BaseCardinality.SINGLE),
      Arguments.of(Cardinality.TO_ONE_OR_NONE, BaseCardinality.SINGLE),
      Arguments.of(Cardinality.TO_MANY, BaseCardinality.MULTI));
  }

  @ParameterizedTest
  @MethodSource("getCardinalitysAndTheirBaseCardinality")
  void testCase_fromSpecification(final Cardinality testUnit, final BaseCardinality expectedBaseCardinality) {
    //execution
    final var result = testUnit.getBaseCardinality();

    //verification
    expect(result).is(expectedBaseCardinality);
  }
}
