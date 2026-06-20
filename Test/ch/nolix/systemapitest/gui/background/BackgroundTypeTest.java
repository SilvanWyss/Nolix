/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.background;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.gui.background.BackgroundType;

/**
 * @author Silvan Wyss
 */
final class BackgroundTypeTest extends StandardTest {
  @MethodSource
  private static IWellOrderContainer<Arguments> getSpecificationsAndTheirBackgroundTypes() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("COLOR"), BackgroundType.COLOR),
      Arguments.of(ImmutableNode.withChildNodes("COLOR_GRADIENT"), BackgroundType.COLOR_GRADIENT),
      Arguments.of(ImmutableNode.withChildNodes("IMAGE"), BackgroundType.IMAGE),
      Arguments.of(ImmutableNode.withChildNodes("TRANSPARENCY"), BackgroundType.TRANSPARENCY));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirBackgroundTypes")
  void testCase_fromSpecification(final INode<?> specification, final BackgroundType expectedBackgroundType) {
    //execution
    final var result = BackgroundType.fromSpecification(specification);

    //verification
    expect(result).is(expectedBackgroundType);
  }
}
