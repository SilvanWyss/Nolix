/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.font;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.gui.font.LineDecoration;

/**
 * @author Silvan Wyss
 */
final class LineDecorationTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirLineDecorations() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("UNDERLINE"), LineDecoration.UNDERLINE),
      Arguments.of(ImmutableNode.withChildNodes("MIDLINE"), LineDecoration.MIDLINE),
      Arguments.of(ImmutableNode.withChildNodes("OVERLINE"), LineDecoration.OVERLINE));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirLineDecorations")
  void testCase_fromSpecification(final Node<?> specification, final LineDecoration expectedLineDecoration) {
    // execute
    final var result = LineDecoration.fromSpecification(specification);

    // verify
    expect(result).is(expectedLineDecoration);
  }
}
