package ch.nolix.coreapitest.datamodelapi.cardinalityapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.coreapi.datamodel.cardinality.Cardinality;

final class CardinalityTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getCardinalitysAndTheirBaseCardinality() {
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
