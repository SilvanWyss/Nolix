package ch.nolix.systemapi.guiapi.presenceapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

final class PresenceTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirPresences() {
    return //
    ImmutableList.withElement(
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
