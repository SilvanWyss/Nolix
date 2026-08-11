/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.gui.background;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.gui.guiproperty.ImageApplication;

/**
 * @author Silvan Wyss
 */
final class ImageApplicationTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirImageApplications() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("SCALE_TO_FRAME"), ImageApplication.SCALE_TO_FRAME),
      Arguments.of(ImmutableNode.withChildNodes("REPEAT"), ImageApplication.REPEAT));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirImageApplications")
  void testCase_fromSpecification(final Node<?> specification, final ImageApplication expectedImageApplication) {
    // execute
    final var result = ImageApplication.fromSpecification(specification);

    // verify
    expect(result).is(expectedImageApplication);
  }
}
