/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.presence;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.gui.guiproperty.Presence;

/**
 * @author Silvan Wyss
 */
final class PresenceTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirPresences() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("VISIBLE"), Presence.VISIBLE),
      Arguments.of(ImmutableNode.withChildNodes("INVISIBLE"), Presence.INVISIBLE),
      Arguments.of(ImmutableNode.withChildNodes("COLLAPSED"), Presence.COLLAPSED));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirPresences")
  void testCase_fromSpecification(final Node<?> specification, final Presence expectedPresence) {
    // execute
    final var result = Presence.fromSpecification(specification);

    // verify
    expect(result).is(expectedPresence);
  }
}
