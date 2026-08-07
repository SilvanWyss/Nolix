/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.background;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.gui.background.BackgroundType;

/**
 * @author Silvan Wyss
 */
final class BackgroundTypeTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirBackgroundTypes() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("COLOR"), BackgroundType.COLOR),
      Arguments.of(ImmutableNode.withChildNodes("COLOR_GRADIENT"), BackgroundType.COLOR_GRADIENT),
      Arguments.of(ImmutableNode.withChildNodes("IMAGE"), BackgroundType.IMAGE),
      Arguments.of(ImmutableNode.withChildNodes("TRANSPARENCY"), BackgroundType.TRANSPARENCY));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirBackgroundTypes")
  void testCase_fromSpecification(final Node<?> specification, final BackgroundType expectedBackgroundType) {
    // execute
    final var result = BackgroundType.fromSpecification(specification);

    // verify
    expect(result).is(expectedBackgroundType);
  }
}
