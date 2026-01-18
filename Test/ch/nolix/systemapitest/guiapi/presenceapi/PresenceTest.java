/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.guiapi.presenceapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.gui.presence.Presence;

/**
 * @author Silvan Wyss
 */
final class PresenceTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirPresences() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNode("VISIBLE"), Presence.VISIBLE),
      Arguments.of(Node.withChildNode("INVISIBLE"), Presence.INVISIBLE),
      Arguments.of(Node.withChildNode("COLLAPSED"), Presence.COLLAPSED));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirPresences")
  void testCase_fromSpecification(final INode<?> specification, final Presence expectedPresence) {
    //execution
    final var result = Presence.fromSpecification(specification);

    //verification
    expect(result).is(expectedPresence);
  }
}
