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
import ch.nolix.systemapi.gui.font.Font;

/**
 * @author Silvan Wyss
 */
final class FontTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirFonts() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("ARIAL"), Font.ARIAL),
      Arguments.of(ImmutableNode.withChildNodes("ARIAL_BLACK"), Font.ARIAL_BLACK),
      Arguments.of(ImmutableNode.withChildNodes("COMIC_SANS_MS"), Font.COMIC_SANS_MS),
      Arguments.of(ImmutableNode.withChildNodes("IMPACT"), Font.IMPACT),
      Arguments.of(ImmutableNode.withChildNodes("LUCIDA_CONSOLE"), Font.LUCIDA_CONSOLE),
      Arguments.of(ImmutableNode.withChildNodes("PAPYRUS"), Font.PAPYRUS),
      Arguments.of(ImmutableNode.withChildNodes("TAHOMA"), Font.TAHOMA),
      Arguments.of(ImmutableNode.withChildNodes("VERDANA"), Font.VERDANA));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirFonts")
  void testCase_fromSpecification(final Node<?> specification, final Font expectedFont) {
    // execute
    final var result = Font.fromSpecification(specification);

    // verify
    expect(result).is(expectedFont);
  }
}
