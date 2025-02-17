package ch.nolix.coreapi.datamodelapi.cardinalityapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class CardinalityTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getCardinalitysAndTheirBaseCardinality() {
    return //
    ImmutableList.withElement(
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
