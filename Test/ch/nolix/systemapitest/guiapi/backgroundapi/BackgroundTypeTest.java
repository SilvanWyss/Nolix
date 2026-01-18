/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.guiapi.backgroundapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.gui.background.BackgroundType;

/**
 * @author Silvan Wyss
 */
final class BackgroundTypeTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirBackgroundTypes() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNode("COLOR"), BackgroundType.COLOR),
      Arguments.of(Node.withChildNode("COLOR_GRADIENT"), BackgroundType.COLOR_GRADIENT),
      Arguments.of(Node.withChildNode("IMAGE"), BackgroundType.IMAGE),
      Arguments.of(Node.withChildNode("TRANSPARENCY"), BackgroundType.TRANSPARENCY));
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
