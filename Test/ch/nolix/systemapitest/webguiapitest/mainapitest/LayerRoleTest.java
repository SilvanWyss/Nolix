//package declaration
package ch.nolix.systemapitest.webguiapitest.mainapitest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
final class LayerRoleTest extends StandardTest {

  //method
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirLayerRole() {
    return //
    ImmutableList.withElement(
      Arguments.of(Node.withChildNode("BACKGROUND_LAYER"), LayerRole.BACKGROUND_LAYER),
      Arguments.of(Node.withChildNode("MAIN_LAYER"), LayerRole.MAIN_LAYER),
      Arguments.of(Node.withChildNode("DIALOG_LAYER"), LayerRole.DIALOG_LAYER));
  }

  //method
  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirLayerRole")
  void fromSpecification(final INode<?> specification, final LayerRole expectedLayerRole) {

    //execution
    final var result = LayerRole.fromSpecification(specification);

    //verification
    expect(result).is(expectedLayerRole);
  }
}
