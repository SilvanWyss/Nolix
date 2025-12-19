package ch.nolix.coretest.document.node;

import ch.nolix.core.document.node.MutableNode;

/**
 * @author Silvan Wyss
 */
final class MutableNodeTest extends BaseMutableNodeTest<MutableNode> {
  @Override
  protected MutableNode createBlankNode() {
    return MutableNode.createEmpty();
  }

  @Override
  protected MutableNode createNodeWithHeader(final String header) {
    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    return mutableNode;
  }

  @Override
  protected MutableNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {
    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    for (final var h : childNodeHeaders) {
      final var childNode = MutableNode.createEmpty();
      childNode.setHeader(h);

      mutableNode.addChildNode(childNode);
    }

    return mutableNode;
  }
}
