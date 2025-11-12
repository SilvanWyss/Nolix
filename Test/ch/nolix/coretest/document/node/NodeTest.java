package ch.nolix.coretest.document.node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;

final class NodeTest extends BaseNodeTest<Node> {
  @Test
  void testCase_asWithHeader_1A() {
    //setup
    final var testUnit = Node.fromString("a(x,y)");

    //execution
    final var result = testUnit.asWithHeader("b");

    //verification
    expect(result).hasStringRepresentation("b(x,y)");
  }

  @Test
  void testCase_asWithHeader_1B() {
    //setup
    final var testUnit = Node.fromString("(x,y)");

    //execution
    final var result = testUnit.asWithHeader("a");

    //verification
    expect(result).hasStringRepresentation("a(x,y)");
  }

  @Test
  void testCase_asWithHeader_1C() {
    //setup
    final var testUnit = Node.fromString("a");

    //execution
    final var result = testUnit.asWithHeader("b");

    //verification
    expect(result).hasStringRepresentation("b");
  }

  @ParameterizedTest
  @CsvSource({
  "a, <a></a>",
  "a(b), <a>b</a>",
  "a(b(c)), <a><b>c</b></a>"
  })
  void testCase_toXml(final String nodeStringRepresentation, final String expectedXmlStringRepresentation) {
    //setup
    final var testUnit = Node.fromString(nodeStringRepresentation);

    //execution
    final var result = testUnit.toXml();

    //verification
    expect(result).hasStringRepresentation(expectedXmlStringRepresentation);
  }

  @Override
  protected Node createBlankNode() {
    return Node.EMPTY_NODE;
  }

  @Override
  protected Node createNodeWithHeader(final String header) {
    return Node.withHeader(header);
  }

  @Override
  protected Node createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {
    return Node.withHeaderAndChildNodes(header, ContainerView.forArray(childNodeHeaders).getViewOf(Node::withHeader));
  }
}
