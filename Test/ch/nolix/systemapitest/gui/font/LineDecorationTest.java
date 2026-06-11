/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.font;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.gui.font.LineDecoration;

/**
 * @author Silvan Wyss
 */
final class LineDecorationTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirLineDecorations() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNodes("UNDERLINE"), LineDecoration.UNDERLINE),
      Arguments.of(Node.withChildNodes("MIDLINE"), LineDecoration.MIDLINE),
      Arguments.of(Node.withChildNodes("OVERLINE"), LineDecoration.OVERLINE));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirLineDecorations")
  void testCase_fromSpecification(final INode<?> specification, final LineDecoration expectedLineDecoration) {
    //execution
    final var result = LineDecoration.fromSpecification(specification);

    //verification
    expect(result).is(expectedLineDecoration);
  }
}
