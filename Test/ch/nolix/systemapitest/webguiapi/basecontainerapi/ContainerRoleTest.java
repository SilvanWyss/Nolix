package ch.nolix.systemapitest.webguiapi.basecontainerapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;

final class ContainerRoleTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirContainerRole() {
    return //
    ImmutableList.withElement(
      Arguments.of(Node.withChildNode("DIALOG_CONTAINER"), ContainerRole.DIALOG_CONTAINER),
      Arguments.of(Node.withChildNode("OVERALL_CONTAINER"), ContainerRole.OVERALL_CONTAINER),
      Arguments.of(Node.withChildNode("MAIN_CONTENT_CONTAINER"), ContainerRole.MAIN_CONTENT_CONTAINER),
      Arguments.of(Node.withChildNode("HEADER_CONTAINER"), ContainerRole.HEADER_CONTAINER),
      Arguments.of(Node.withChildNode("FOOTER_CONTAINER"), ContainerRole.FOOTER_CONTAINER),
      Arguments.of(Node.withChildNode("COMPONENT_CONTAINER"), ContainerRole.COMPONENT_CONTAINER),
      Arguments.of(Node.withChildNode("TITLE_CONTAINER"), ContainerRole.TITLE_CONTAINER));
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
