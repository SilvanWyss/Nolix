/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.background;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.gui.background.ImageApplication;

/**
 * @author Silvan Wyss
 */
final class ImageApplicationTest extends StandardTest {
  @MethodSource
  private static IWellOrderContainer<Arguments> getSpecificationsAndTheirImageApplications() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNodes("SCALE_TO_FRAME"), ImageApplication.SCALE_TO_FRAME),
      Arguments.of(Node.withChildNodes("REPEAT"), ImageApplication.REPEAT));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirImageApplications")
  void testCase_fromSpecification(final INode<?> specification, final ImageApplication expectedImageApplication) {
    //execution
    final var result = ImageApplication.fromSpecification(specification);

    //verification
    expect(result).is(expectedImageApplication);
  }
}
