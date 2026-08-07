/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.webgui.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
final class LayerRoleTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirLayerRole() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("BACKGROUND_LAYER"), LayerRole.BACKGROUND_LAYER),
      Arguments.of(ImmutableNode.withChildNodes("MAIN_LAYER"), LayerRole.MAIN_LAYER),
      Arguments.of(ImmutableNode.withChildNodes("DIALOG_LAYER"), LayerRole.DIALOG_LAYER));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirLayerRole")
  void testCase_fromSpecification(final Node<?> specification, final LayerRole expectedLayerRole) {
    // execute
    final var result = LayerRole.fromSpecification(specification);

    // verify
    expect(result).is(expectedLayerRole);
  }
}
