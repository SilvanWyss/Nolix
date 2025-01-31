package ch.nolix.core.document.node;

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

    for (final var cnh : childNodeHeaders) {

      final var childNode = MutableNode.createEmpty();
      childNode.setHeader(cnh);

      mutableNode.addChildNode(childNode);
    }

    return mutableNode;
  }
}
