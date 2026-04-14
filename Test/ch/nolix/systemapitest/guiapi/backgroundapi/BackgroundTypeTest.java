/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.guiapi.backgroundapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.gui.background.BackgroundType;

/**
 * @author Silvan Wyss
 */
final class BackgroundTypeTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirBackgroundTypes() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNodes("COLOR"), BackgroundType.COLOR),
      Arguments.of(Node.withChildNodes("COLOR_GRADIENT"), BackgroundType.COLOR_GRADIENT),
      Arguments.of(Node.withChildNodes("IMAGE"), BackgroundType.IMAGE),
      Arguments.of(Node.withChildNodes("TRANSPARENCY"), BackgroundType.TRANSPARENCY));
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
