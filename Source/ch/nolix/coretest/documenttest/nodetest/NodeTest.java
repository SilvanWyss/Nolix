//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class NodeTest extends BaseNodeTest<Node> {

  //method
  @TestCase
  public void testCase_asWithHeader_1A() {

    //setup
    final var testUnit = Node.fromString("a(x,y)");

    //execution
    final var result = testUnit.asWithHeader("b");

    //verification
    expect(result).hasStringRepresentation("b(x,y)");
  }

  //method
  @TestCase
  public void testCase_asWithHeader_1B() {

    //setup
    final var testUnit = Node.fromString("(x,y)");

    //execution
    final var result = testUnit.asWithHeader("a");

    //verification
    expect(result).hasStringRepresentation("a(x,y)");
  }

  //method
  @TestCase
  public void testCase_asWithHeader_1C() {

    //setup
    final var testUnit = Node.fromString("a");

    //execution
    final var result = testUnit.asWithHeader("b");

    //verification
    expect(result).hasStringRepresentation("b");
  }

  //method
  @Override
  protected Node createBlankNode() {
    return Node.EMPTY_NODE;
  }

  //method
  @Override
  protected Node createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {
    return Node.withHeaderAndChildNodes(header, ReadContainer.forArray(childNodeHeaders).to(Node::withHeader));
  }
}
