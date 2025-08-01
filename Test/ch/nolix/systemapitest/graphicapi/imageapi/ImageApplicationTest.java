package ch.nolix.systemapitest.graphicapi.imageapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.graphic.image.ImageApplication;

final class ImageApplicationTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirImageApplications() {
    return //
    ImmutableList.withElement(
      Arguments.of(Node.withChildNode("SCALE_TO_FRAME"), ImageApplication.SCALE_TO_FRAME),
      Arguments.of(Node.withChildNode("REPEAT"), ImageApplication.REPEAT));
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
