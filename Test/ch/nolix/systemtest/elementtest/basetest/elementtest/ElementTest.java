//package declaration
package ch.nolix.systemtest.elementtest.basetest.elementtest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.base.Element;

//class
final class ElementTest extends StandardTest {

  //method
  @Test
  void testCase_hashCode() {

    //setup
    final var testUnit = new Element() {

      //method
      @Override
      public IContainer<INode<?>> getAttributes() {
        return ImmutableList.withElement(Node.withHeader("my_flag"));
      }
    };

    //verification setup
    final var expectedResult = testUnit.getSpecification().hashCode();

    //execution
    final var result = testUnit.hashCode();

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
