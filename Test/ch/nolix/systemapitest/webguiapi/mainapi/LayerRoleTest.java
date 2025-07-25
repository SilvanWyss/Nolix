package ch.nolix.systemapitest.webguiapi.mainapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

final class LayerRoleTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirLayerRole() {
    return //
    ImmutableList.withElement(
      Arguments.of(Node.withChildNode("BACKGROUND_LAYER"), LayerRole.BACKGROUND_LAYER),
      Arguments.of(Node.withChildNode("MAIN_LAYER"), LayerRole.MAIN_LAYER),
      Arguments.of(Node.withChildNode("DIALOG_LAYER"), LayerRole.DIALOG_LAYER));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirLayerRole")
  void testCase_fromSpecification(final INode<?> specification, final LayerRole expectedLayerRole) {

    //execution
    final var result = LayerRole.fromSpecification(specification);

    //verification
    expect(result).is(expectedLayerRole);
  }
}
