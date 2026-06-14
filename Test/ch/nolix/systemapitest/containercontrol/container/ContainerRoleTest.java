/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.containercontrol.container;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.containercontrol.container.ContainerRole;

/**
 * @author Silvan Wyss
 */
final class ContainerRoleTest extends StandardTest {
  @MethodSource
  private static IWellOrderContainer<Arguments> getSpecificationsAndTheirContainerRole() {
    return //
    ImmutableList.withElements(
      Arguments.of(Node.withChildNodes("DIALOG_CONTAINER"), ContainerRole.DIALOG_CONTAINER),
      Arguments.of(Node.withChildNodes("OVERALL_CONTAINER"), ContainerRole.OVERALL_CONTAINER),
      Arguments.of(Node.withChildNodes("MAIN_CONTENT_CONTAINER"), ContainerRole.MAIN_CONTENT_CONTAINER),
      Arguments.of(Node.withChildNodes("HEADER_CONTAINER"), ContainerRole.HEADER_CONTAINER),
      Arguments.of(Node.withChildNodes("FOOTER_CONTAINER"), ContainerRole.FOOTER_CONTAINER),
      Arguments.of(Node.withChildNodes("COMPONENT_CONTAINER"), ContainerRole.COMPONENT_CONTAINER),
      Arguments.of(Node.withChildNodes("TITLE_CONTAINER"), ContainerRole.TITLE_CONTAINER));
  }

  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirContainerRole")
  void testCase_fromSpecification(final INode<?> specification, final ContainerRole expectedContainerRole) {
    //execution
    final var result = ContainerRole.fromSpecification(specification);

    //verification
    expect(result).is(expectedContainerRole);
  }
}
