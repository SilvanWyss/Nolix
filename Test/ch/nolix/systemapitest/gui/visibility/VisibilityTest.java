/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.visibility;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.gui.guiproperty.Visibility;

/**
 * @author Silvan Wyss
 */
final class VisibilityTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirVisibility() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("VISIBLE"), Visibility.VISIBLE),
      Arguments.of(ImmutableNode.withChildNodes("INVISIBLE"), Visibility.INVISIBLE));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirVisibility")
  void testCase_fromSpecification(final Node<?> specification, final Visibility expectedVisibility) {
    // execute
    final var result = Visibility.fromSpecification(specification);

    // verify
    expect(result).is(expectedVisibility);
  }
}
