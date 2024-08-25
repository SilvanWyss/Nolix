//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.MutableNode;

//class
final class MutableNodeTest extends BaseMutableNodeTest<MutableNode> {

  //method
  @Override
  protected MutableNode createBlankNode() {
    return MutableNode.createEmpty();
  }

  //method
  @Override
  protected MutableNode createNodeWithHeader(final String header) {

    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    return mutableNode;
  }

  //method
  @Override
  protected MutableNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {

    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    for (final var cnh : childNodeHeaders) {

      final var childNode = MutableNode.createEmpty();
      childNode.setHeader(cnh);

      mutableNode.addChildNode(childNode);
    }

    return mutableNode;
  }
}
