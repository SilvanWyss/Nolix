/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.webgui.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
final class LayerRoleTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirLayerRole() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNodes("BACKGROUND_LAYER"), LayerRole.BACKGROUND_LAYER),
      Arguments.of(Node.withChildNodes("MAIN_LAYER"), LayerRole.MAIN_LAYER),
      Arguments.of(Node.withChildNodes("DIALOG_LAYER"), LayerRole.DIALOG_LAYER));
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
