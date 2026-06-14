/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

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
    this.nullableHeader = null;
    this.childNodes = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link Node} with the given childNodes.
   * 
   * @param childNodes
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  private Node(final IWellOrderContainer<Node> childNodes) {
    this.nullableHeader = null;
    this.childNodes = ImmutableList.fromIterable(childNodes);
  }

  /**
   * Creates a new {@link Node} with the given header.
   * 
   * @param header
   * @throws RuntimeException if the given header is null or blank.
   */
  private Node(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableCatalog.HEADER).isNotBlank();

    this.nullableHeader = header;
    this.childNodes = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link Node} with the given header and childNodes.
   * 
   * @param header
   * @param childNodes
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  private Node(final String header, final IWellOrderContainer<Node> childNodes) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableCatalog.HEADER).isNotBlank();

    this.nullableHeader = header;
    this.childNodes = ImmutableList.fromIterable(childNodes);
  }

  /**
   * @param paramEnum
   * @return a new {@link Node} from the given paramEnum. throws RuntimeException
   *         if the given paramEnum is null.
   */
  public static Node fromEnum(final Enum<?> paramEnum) {
    final var header = getTypeNameOfEnum(paramEnum);
    final var childNodeHeader = paramEnum.name();
    final var childNode = withHeader(childNodeHeader);
    final var childNodes = ImmutableList.withElement(childNode);

    return new Node(header, childNodes);
  }

  /**
   * @param filePath
   * @return a new {@link Node} from the file with the given filePath.
   * @throws RuntimeException if the given filePath is not valid.
   * @throws RuntimeException if the file with the given filePath does not
   *                          represent a {@link Node}.
   */
  public static Node fromFile(final String filePath) {
    final var mutableNode = MutableNode.fromFile(filePath);

    return fromNode(mutableNode);
  }

  /**
   * @param node
   * @return a new {@link Node} from the given {@link INode}.
   * @throws RuntimeException if the given node is null.
   */
  public static Node fromNode(final INode<?> node) {
    if (node instanceof final Node localNode) {
      return localNode;
    }

    final var childNodes = node.getStoredChildNodes().getViewOf(Node::fromNode);

    if (node.hasHeader()) {
      final var header = node.getHeader();

      return new Node(header, childNodes);
    }

    return new Node(childNodes);
  }

  /**
   * @param string
   * @return a new {@link Node} from the given string.
   * @throws RuntimeException if the given string does not represent a
   *                          {@link Node}.
   */
  public static Node fromString(final String string) {
    final var mutableNode = MutableNode.fromString(string);

    return fromNode(mutableNode);
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final boolean childNode) {
    final var booleanChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(booleanChildNode);

    return new Node(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final double childNode) {
    final var doubleChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(doubleChildNode);

    return new Node(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   * @throws RuntimeException if the given childNode is null.
   */
  public static Node withChildNode(final INode<?> childNode) {
    final var nodeChildNode = fromNode(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new Node(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final long childNode) {
    final var longChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(longChildNode);

    return new Node(childNodes);
  }

  /**
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  public static Node withChildNodes(final INode<?>... childNodes) {
    final var childNodesContainer = ContainerView.forArray(childNodes).getViewOf(Node::fromNode);

    return new Node(childNodesContainer);
  }

  /**
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  public static Node withChildNodes(final Iterable<? extends INode<?>> childNodes) {
    final var childNodesContainer = ContainerView.forIterable(childNodes).getViewOf(Node::fromNode);

    return new Node(childNodesContainer);
  }

  /**
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes does not represent a
   *                          {@link Node}.
   */
  public static Node withChildNodes(final String... childNodes) {
    final var childNodesContainer = ContainerView.forArray(childNodes).getViewOf(Node::fromString);

    return new Node(childNodesContainer);
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final boolean header) {
    final var booleanHeader = String.valueOf(header);

    return new Node(booleanHeader);
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final double header) {
    final var doubleHeader = String.valueOf(header);

    return new Node(doubleHeader);
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final long header) {
    final var longHeader = String.valueOf(header);

    return new Node(longHeader);
  }

  /**
   * @param header
   * @return a new {@link Node} with the given header.
   * @throws RuntimeException if the given header is null or blank.
   */
  public static Node withHeader(final String header) {
    return new Node(header);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   * @throws RuntimeException if the given header is null or blank.
   */
  public static Node withHeaderAndChildNode(final String header, final boolean childNode) {
    final var booleanChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(booleanChildNode);

    return new Node(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode. throws RuntimeException
   *         if the given header is null or blank.
   */
  public static Node withHeaderAndChildNode(final String header, final double childNode) {
    final var doubleChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(doubleChildNode);

    return new Node(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given header and childNode.
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNode is null.
   */
  public static Node withHeaderAndChildNode(final String header, final INode<?> childNode) {
    final var nodeChildNode = fromNode(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new Node(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given childNode. throws RuntimeException
   *         if the given header is null or blank.
   */
  public static Node withHeaderAndChildNode(final String header, final long childNode) {
    final var longChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(longChildNode);

    return new Node(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link Node} with the given header and childNode.
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNode does not represent a
   *                          {@link Node}.
   */
  public static Node withHeaderAndChildNode(final String header, final String childNode) {
    final var nodeChildNode = fromString(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new Node(header, childNodes);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link Node} with the given header and childNodes.
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  public static Node withHeaderAndChildNodes(final String header, final INode<?>... childNodes) {
    final var childNodesContainer = ContainerView.forArray(childNodes).getViewOf(Node::fromNode);

    return new Node(header, childNodesContainer);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link Node} with the given header and childNodes.
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  public static Node withHeaderAndChildNodes(final String header, final Iterable<? extends INode<?>> childNodes) {
    final var childNodesContainer = ContainerView.forIterable(childNodes).getViewOf(Node::fromNode);

    return new Node(header, childNodesContainer);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link Node} with the given header and childNodes.
   * @throws RuntimeException if the given header is null or blank.
   * @throws RuntimeException if the given childNodes is null.
   * @throws RuntimeException if one of the given childNodes does not represent a
   *                          {@link Node}.
   */
  public static Node withHeaderAndChildNodes(final String header, final String... childNodes) {
    final var childNodeContainer = ContainerView.forArray(childNodes).getViewOf(Node::fromString);

    return new Node(header, childNodeContainer);
  }

  /**
   * @param paramEnum
   * @return the name of the type of the given paramEnum.
   */
  private static String getTypeNameOfEnum(final Enum<?> paramEnum) {
    return paramEnum.getClass().getSimpleName();
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
  public IWellOrderContainer<Node> getStoredChildNodes() {
    return childNodes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return (nullableHeader != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> withNewHeader(String header) {
    return withHeaderAndChildNodes(header, getStoredChildNodes());
  }
}
