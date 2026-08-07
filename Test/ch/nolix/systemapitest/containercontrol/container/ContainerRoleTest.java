/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.containercontrol.container;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.control.container.ContainerRole;

/**
 * @author Silvan Wyss
 */
final class ContainerRoleTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getSpecificationsAndTheirContainerRole() {
    return //
    ImmutableList.withElements(
      Arguments.of(ImmutableNode.withChildNodes("DIALOG_CONTAINER"), ContainerRole.DIALOG_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("OVERALL_CONTAINER"), ContainerRole.OVERALL_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("MAIN_CONTENT_CONTAINER"), ContainerRole.MAIN_CONTENT_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("HEADER_CONTAINER"), ContainerRole.HEADER_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("FOOTER_CONTAINER"), ContainerRole.FOOTER_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("COMPONENT_CONTAINER"), ContainerRole.COMPONENT_CONTAINER),
      Arguments.of(ImmutableNode.withChildNodes("TITLE_CONTAINER"), ContainerRole.TITLE_CONTAINER));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirContainerRole")
  void testCase_fromSpecification(final Node<?> specification, final ContainerRole expectedContainerRole) {
    // execute
    final var result = ContainerRole.fromSpecification(specification);

    // verify
    expect(result).is(expectedContainerRole);
  }
}
