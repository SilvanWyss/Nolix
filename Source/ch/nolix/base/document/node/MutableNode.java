/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import java.util.function.Predicate;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class MutableNode extends AbstractMutableNode<MutableNode> {
  private String memberHeader;

  private final LinkedList<MutableNode> memberChildNodes = LinkedList.createEmpty();

  /**
   * Creates a new empty {@link MutableNode}.
   */
  private MutableNode() {
  }

  /**
   * @return a new empty {@link MutableNode}.
   */
  public static MutableNode createEmpty() {
    return new MutableNode();
  }

  /**
   * @param filePath
   * @return a new {@link MutableNode} from the file with the given filePath
   * @throws RuntimeException if the given filePath is not valid
   * @throws RuntimeException if the file with the given filePath does not
   *                          represent a {@link MutableNode}.
   */
  public static MutableNode fromFile(final String filePath) {
    final var mutableNode = MutableNode.createEmpty();
    mutableNode.resetFromFile(filePath);

    return mutableNode;
  }

  /**
   * @param node
   * @return a new {@link MutableNode} from the given {@link Node}.
   */
  public static MutableNode fromNode(final Node<?> node) {
    final var mutableNode = MutableNode.createEmpty();

    if (node.hasHeader()) {
      mutableNode.setHeader(node.getHeader());
    }

    mutableNode.addChildNodes(node.getStoredChildNodes());

    return mutableNode;
  }

  /**
   * @param string
   * @return a new {@link MutableNode} from the given string
   * @throws RuntimeException if the given string does not represent a
   *                          {@link MutableNode}.
   */
  public static MutableNode fromString(final String string) {
    final var mutableNode = MutableNode.createEmpty();
    mutableNode.resetFromString(string);

    return mutableNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNode(final Node<?> childNode) {
    memberChildNodes.addAtEnd(fromNode(childNode));

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNodes(final Node<?>... childNodes) {
    for (final var c : childNodes) {
      addChildNode(c);
    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <N extends Node<?>> MutableNode addChildNodes(final Iterable<N> pChildNodes) {
    for (final var c : pChildNodes) {
      memberChildNodes.addAtEnd(fromNode(c));
    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNodesFromStrings(final Iterable<String> strings) {
    for (final var s : strings) {
      addChildNode(fromString(s));
    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode addChildNodesFromStrings(final String... strings) {
    for (final var s : strings) {
      addChildNode(fromString(s));
    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> withNewHeader(String header) {
    return ImmutableNode.withHeaderAndChildNodes(header, getStoredChildNodes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<MutableNode> getStoredChildNodes() {
    return memberChildNodes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {
    assertHasHeader();

    return memberHeader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return (memberHeader != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeChildNodes() {
    memberChildNodes.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode removeAndGetStoredFirstChildNodeThat(final Predicate<Node<?>> selector) {
    return memberChildNodes.removeAndGetStoredFirst(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeThat(final Predicate<Node<?>> selector) {
    memberChildNodes.removeFirst(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirstChildNodeWithHeader(String header) {
    memberChildNodes.removeFirst(cn -> cn.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeHeader() {
    memberHeader = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void replaceFirstChildNodeWithGivenHeaderByGivenNode(final String header, final Node<?> childNode) {
    memberChildNodes.replaceFirst(a -> a.hasHeader(header), fromNode(childNode));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    removeHeader();
    removeChildNodes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableNode setHeader(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableNameCatalog.HEADER).isNotBlank();

    memberHeader = header;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MutableNode asConcrete() {
    return this;
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link MutableNode} does not
   *                                               have a header.
   */
  private void assertHasHeader() {
    if (!hasHeader()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.HEADER);
    }
  }
}
