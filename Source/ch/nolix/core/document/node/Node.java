/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.document.node;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link Node} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class Node extends AbstractNode<Node> {
  public static final Node EMPTY_NODE = new Node();

  private final String nullableHeader;

  private final ImmutableList<Node> childNodes;

  /**
   * Creates a new {@link Node}.
   */
  private Node() {
    nullableHeader = null;
    childNodes = ImmutableList.fromIterable(LinkedList.createEmpty());
  }

  /**
   * Creates a new {@link Node} with the given childNodes.
   * 
   * @param childNodes
   */
  private Node(final Iterable<? extends INode<?>> childNodes) {
    this.nullableHeader = null;
    this.childNodes = ImmutableList.fromIterable(createNodesFromNodes(childNodes));
  }

  /**
   * Creates a new {@link Node} with the given header.
   * 
   * @param header
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private Node(final String header) {
    this.nullableHeader = getValidHeaderFromHeader(header);
    this.childNodes = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link Node} with the given header and childNodes.
   * 
   * @param header
   * @param childNode
   * @param childNodes
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private Node(final String header, final INode<?> childNode, final INode<?>[] childNodes) {
    this.nullableHeader = getValidHeaderFromHeader(header);

    this.childNodes = //
    ImmutableList.fromIterable(createNodesFromNodes(ContainerView.forElementAndArray(childNode, childNodes)));
  }

  /**
   * Creates a new {@link Node} with the given header and childNodes.
   * 
   * @param header
   * @param childNodes
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private Node(final String header, final Iterable<? extends INode<?>> childNodes) {
    this.nullableHeader = getValidHeaderFromHeader(header);
    this.childNodes = ImmutableList.fromIterable(createNodesFromNodes(childNodes));
  }

  /**
   * @param pEnum
   * @return a new {@link Node} from the given pEnum.
   */
  public static Node fromEnum(final Enum<?> pEnum) {
    return withHeaderAndChildNode(getTypeNameOfEnum(pEnum), withHeader(pEnum.name()));
  }

  /**
   * @param filePath
   * @return a new {@link Node} from the file with the given filePath.
   * @throws InvalidArgumentException        if the given filePath is not valid.
   * @throws UnrepresentingArgumentException if the file with the given filePath
   *                                         does not represent a {@link Node}.
   */
  public static Node fromFile(final String filePath) {
    return fromNode(MutableNode.fromFile(filePath));
  }

  /**
   * @param node
   * @return a new {@link Node} from the given {@link INode}.
   */
  public static Node fromNode(final INode<?> node) {
    if (node instanceof final Node lNode) {
      return lNode;
    }

    if (!node.hasHeader()) {
      return withChildNodes(node.getStoredChildNodes());
    }

    return withHeaderAndChildNodes(node.getHeader(), node.getStoredChildNodes());
  }

  /**
   * @param string
   * @return a new {@link Node} from the given string.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link Node}.
   */
  public static Node fromString(final String string) {
    return fromNode(MutableNode.fromString(string));
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final boolean childNode) {
    return withChildNode(withHeader(childNode));
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final double childNode) {
    return withChildNode(withHeader(childNode));
  }

  /**
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   */
  public static Node withChildNode(final INode<?> childNode, final INode<?>... childNodes) {
    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes);

    return new Node(allChildNodes);
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final long childNode) {
    return withChildNode(withHeader(childNode));
  }

  /**
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withChildNode(final String childNode, final String... childNodes) {
    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes).getViewOf(Node::withHeader);

    return withChildNodes(allChildNodes);
  }

  /**
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   */
  public static Node withChildNodes(final Iterable<? extends INode<?>> childNodes) {
    return new Node(childNodes);
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final boolean header) {
    return withHeader(String.valueOf(header));
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final double header) {
    return withHeader(String.valueOf(header));
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final long header) {
    return withHeader(String.valueOf(header));
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeader(final String header) {
    return new Node(header);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeaderAndChildNode(final String header, final boolean childNode) {
    return withHeaderAndChildNode(header, withHeader(childNode));
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeaderAndChildNode(final String header, final double childNode) {
    return withHeaderAndChildNode(header, withHeader(childNode));
  }

  /**
   * @param header
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given header and childNodes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeaderAndChildNode(
    final String header,
    final INode<?> childNode,
    final INode<?>... childNodes) {
    return new Node(header, childNode, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeaderAndChildNode(final String header, final long childNode) {
    return withHeaderAndChildNode(header, withHeader(childNode));
  }

  /**
   * @param header
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given header and childNodes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if one of the given childNodes is null.
   * @throws InvalidArgumentException if one of the given childNodes is blank.
   */
  public static Node withHeaderAndChildNode(final String header, final String childNode, final String... childNodes) {
    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes).getViewOf(Node::withHeader);

    return withHeaderAndChildNodes(header, allChildNodes);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeaderAndChildNodes(final String header, final Iterable<? extends INode<?>> childNodes) {
    return new Node(header, childNodes);
  }

  /**
   * @param nodes
   * @return new {@link Node}s from the given nodes.
   * @throws RuntimeException if one of the given nodes is null.
   */
  private static IContainer<Node> createNodesFromNodes(final Iterable<? extends INode<?>> nodes) {
    final ILinkedList<Node> lNodes = LinkedList.createEmpty();

    for (final var n : nodes) {
      lNodes.addAtEnd(fromNode(n));
    }

    return lNodes;
  }

  /**
   * @param pEnum
   * @return the name of the type of the given pEnum.
   */
  private static String getTypeNameOfEnum(final Enum<?> pEnum) {
    return pEnum.getClass().getSimpleName();
  }

  /**
   * @param header
   * @return a valid header from the given header.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private static String getValidHeaderFromHeader(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableCatalog.HEADER).isNotBlank();

    return header;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> asWithHeader(String header) {
    return withHeaderAndChildNodes(header, getStoredChildNodes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {
    if (nullableHeader == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.HEADER);
    }

    return nullableHeader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<Node> getStoredChildNodes() {
    return childNodes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return (nullableHeader != null);
  }
}
