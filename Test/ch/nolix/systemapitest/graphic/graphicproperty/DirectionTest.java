/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.graphic.graphicproperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.graphic.graphicproperty.Direction;

/**
 * @author Silvan Wyss
 */
final class DirectionTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirDirections() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("HORIZONTAL"), Direction.HORIZONTAL),
      Arguments.of(ImmutableNode.withChildNodes("VERTICAL"), Direction.VERTICAL),
      Arguments.of(ImmutableNode.withChildNodes("DIAGONAL_LEFT_UP"), Direction.DIAGONAL_LEFT_UP),
      Arguments.of(ImmutableNode.withChildNodes("DIAGONAL_LEFT_DOWN"), Direction.DIAGONAL_LEFT_DOWN));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirDirections")
  void testCase_fromSpecification(final Node<?> specification, final Direction expectedDirection) {
    // execute
    final var result = Direction.fromSpecification(specification);

    // verify
    expect(result).is(expectedDirection);
  }
}
