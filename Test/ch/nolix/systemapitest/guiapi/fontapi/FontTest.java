/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.guiapi.fontapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.gui.font.Font;

/**
 * @author Silvan Wyss
 */
final class FontTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirFonts() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNode("ARIAL"), Font.ARIAL),
      Arguments.of(Node.withChildNode("ARIAL_BLACK"), Font.ARIAL_BLACK),
      Arguments.of(Node.withChildNode("COMIC_SANS_MS"), Font.COMIC_SANS_MS),
      Arguments.of(Node.withChildNode("IMPACT"), Font.IMPACT),
      Arguments.of(Node.withChildNode("LUCIDA_CONSOLE"), Font.LUCIDA_CONSOLE),
      Arguments.of(Node.withChildNode("PAPYRUS"), Font.PAPYRUS),
      Arguments.of(Node.withChildNode("TAHOMA"), Font.TAHOMA),
      Arguments.of(Node.withChildNode("VERDANA"), Font.VERDANA));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirFonts")
  void testCase_fromSpecification(final INode<?> specification, final Font expectedFont) {
    //execution
    final var result = Font.fromSpecification(specification);

    //verification
    expect(result).is(expectedFont);
  }
}
