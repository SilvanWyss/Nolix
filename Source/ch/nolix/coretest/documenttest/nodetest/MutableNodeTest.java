//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.MutableNode;

//class
public final class MutableNodeTest extends BaseMutableNodeTest<MutableNode> {

  //method
  @Override
  protected MutableNode createBlankNode() {
    return new MutableNode();
  }

  //method
  @Override
  protected MutableNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {

    final var mutableNode = new MutableNode();

    mutableNode.setHeader(header);

    for (final var cnh : childNodeHeaders) {

      final var childNode = new MutableNode();
      childNode.setHeader(cnh);

      mutableNode.addChildNode(childNode);
    }

    return mutableNode;
  }
}
