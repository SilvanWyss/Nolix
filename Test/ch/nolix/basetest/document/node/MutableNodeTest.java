/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.node;

import ch.nolix.base.document.node.MutableNode;

/**
 * @author Silvan Wyss
 */
final class MutableNodeTest extends BaseMutableNodeTest<MutableNode> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected MutableNode createBlankNode() {
    return MutableNode.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MutableNode createNodeWithHeader(final String header) {
    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    return mutableNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MutableNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodes) {
    final var mutableNode = MutableNode.createEmpty();

    mutableNode.setHeader(header);

    for (final var c : childNodes) {
      final var childNode = MutableNode.fromString(c);

      mutableNode.addChildNode(childNode);
    }

    return mutableNode;
  }
}
