/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableNode extends AbstractNode<ImmutableNode> {
  public static final ImmutableNode EMPTY_NODE = new ImmutableNode();

  private final String nullableHeader;

  private final ImmutableList<ImmutableNode> childNodes;

  /**
   * Creates a new {@link ImmutableNode}.
   */
  private ImmutableNode() {
    this.nullableHeader = null;
    this.childNodes = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link ImmutableNode} with the given childNodes.
   * 
   * @param childNodes
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  private ImmutableNode(final ExtendedIterable<ImmutableNode> childNodes) {
    this.nullableHeader = null;
    this.childNodes = ImmutableList.fromIterable(childNodes);
  }

  /**
   * Creates a new {@link ImmutableNode} with the given header.
   * 
   * @param header
   * @throws RuntimeException if the given header is null or blank
   */
  private ImmutableNode(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableNameCatalog.HEADER).isNotBlank();

    this.nullableHeader = header;
    this.childNodes = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link ImmutableNode} with the given header and childNodes.
   * 
   * @param header
   * @param childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  private ImmutableNode(final String header, final ExtendedIterable<ImmutableNode> childNodes) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableNameCatalog.HEADER).isNotBlank();

    this.nullableHeader = header;
    this.childNodes = ImmutableList.fromIterable(childNodes);
  }

  /**
   * @param paramEnum
   * @return a new {@link ImmutableNode} from the given paramEnum. throws
   *         RuntimeException if the given paramEnum is null
   */
  public static ImmutableNode fromEnum(final Enum<?> paramEnum) {
    final var header = getTypeNameOfEnum(paramEnum);
    final var childNodeHeader = paramEnum.name();
    final var childNode = withHeader(childNodeHeader);
    final var childNodes = ImmutableList.withElement(childNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param filePath
   * @return a new {@link ImmutableNode} from the file with the given filePath
   * @throws RuntimeException if the given filePath is not valid
   * @throws RuntimeException if the file with the given filePath does not
   *                          represent a {@link ImmutableNode}.
   */
  public static ImmutableNode fromFile(final String filePath) {
    final var mutableNode = MutableNode.fromFile(filePath);

    return fromNode(mutableNode);
  }

  /**
   * @param node
   * @return a new {@link ImmutableNode} from the given {@link Node}
   * @throws RuntimeException if the given node is null
   */
  public static ImmutableNode fromNode(final Node<?> node) {
    if (node instanceof final ImmutableNode localNode) {
      return localNode;
    }

    final var childNodes = node.getStoredChildNodes().getViewOf(ImmutableNode::fromNode);

    if (node.hasHeader()) {
      final var header = node.getHeader();

      return new ImmutableNode(header, childNodes);
    }

    return new ImmutableNode(childNodes);
  }

  /**
   * @param string
   * @return a new {@link ImmutableNode} from the given string
   * @throws RuntimeException if the given string does not represent a
   *                          {@link ImmutableNode}.
   */
  public static ImmutableNode fromString(final String string) {
    final var mutableNode = MutableNode.fromString(string);

    return fromNode(mutableNode);
  }

  /**
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode.
   */
  public static ImmutableNode withChildNode(final boolean childNode) {
    final var booleanChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(booleanChildNode);

    return new ImmutableNode(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode.
   */
  public static ImmutableNode withChildNode(final double childNode) {
    final var doubleChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(doubleChildNode);

    return new ImmutableNode(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode
   * @throws RuntimeException if the given childNode is null
   */
  public static ImmutableNode withChildNode(final Node<?> childNode) {
    final var nodeChildNode = fromNode(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new ImmutableNode(childNodes);
  }

  /**
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode.
   */
  public static ImmutableNode withChildNode(final long childNode) {
    final var longChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(longChildNode);

    return new ImmutableNode(childNodes);
  }

  /**
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given childNodes
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ImmutableNode withChildNodes(final Node<?>... childNodes) {
    final var childNodesContainer = ExtendedIterableView.forArray(childNodes).getViewOf(ImmutableNode::fromNode);

    return new ImmutableNode(childNodesContainer);
  }

  /**
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given childNodes
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ImmutableNode withChildNodes(final Iterable<? extends Node<?>> childNodes) {
    final var childNodesContainer = ExtendedIterableView.forIterable(childNodes).getViewOf(ImmutableNode::fromNode);

    return new ImmutableNode(childNodesContainer);
  }

  /**
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given childNodes
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes does not represent a
   *                          {@link ImmutableNode}.
   */
  public static ImmutableNode withChildNodes(final String... childNodes) {
    final var childNodesContainer = ExtendedIterableView.forArray(childNodes).getViewOf(ImmutableNode::fromString);

    return new ImmutableNode(childNodesContainer);
  }

  /**
   * @param header
   * @return a new {@link ImmutableNode} with the given header.
   */
  public static ImmutableNode withHeader(final boolean header) {
    final var booleanHeader = String.valueOf(header);

    return new ImmutableNode(booleanHeader);
  }

  /**
   * @param header
   * @return a new {@link ImmutableNode} with the given header.
   */
  public static ImmutableNode withHeader(final double header) {
    final var doubleHeader = String.valueOf(header);

    return new ImmutableNode(doubleHeader);
  }

  /**
   * @param header
   * @return a new {@link ImmutableNode} with the given header.
   */
  public static ImmutableNode withHeader(final long header) {
    final var longHeader = String.valueOf(header);

    return new ImmutableNode(longHeader);
  }

  /**
   * @param header
   * @return a new {@link ImmutableNode} with the given header
   * @throws RuntimeException if the given header is null or blank
   */
  public static ImmutableNode withHeader(final String header) {
    return new ImmutableNode(header);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode
   * @throws RuntimeException if the given header is null or blank
   */
  public static ImmutableNode withHeaderAndChildNode(final String header, final boolean childNode) {
    final var booleanChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(booleanChildNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode. throws
   *         RuntimeException if the given header is null or blank
   */
  public static ImmutableNode withHeaderAndChildNode(final String header, final double childNode) {
    final var doubleChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(doubleChildNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ImmutableNode} with the given header and childNode
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNode is null
   */
  public static ImmutableNode withHeaderAndChildNode(final String header, final Node<?> childNode) {
    final var nodeChildNode = fromNode(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ImmutableNode} with the given childNode. throws
   *         RuntimeException if the given header is null or blank
   */
  public static ImmutableNode withHeaderAndChildNode(final String header, final long childNode) {
    final var longChildNode = withHeader(childNode);
    final var childNodes = ImmutableList.withElement(longChildNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ImmutableNode} with the given header and childNode
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNode does not represent a
   *                          {@link ImmutableNode}.
   */
  public static ImmutableNode withHeaderAndChildNode(final String header, final String childNode) {
    final var nodeChildNode = fromString(childNode);
    final var childNodes = ImmutableList.withElement(nodeChildNode);

    return new ImmutableNode(header, childNodes);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given header and childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ImmutableNode withHeaderAndChildNodes(final String header, final Node<?>... childNodes) {
    final var childNodesContainer = ExtendedIterableView.forArray(childNodes).getViewOf(ImmutableNode::fromNode);

    return new ImmutableNode(header, childNodesContainer);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given header and childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ImmutableNode withHeaderAndChildNodes(final String header,
    final Iterable<? extends Node<?>> childNodes) {
    final var childNodesContainer = ExtendedIterableView.forIterable(childNodes).getViewOf(ImmutableNode::fromNode);

    return new ImmutableNode(header, childNodesContainer);
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link ImmutableNode} with the given header and childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes does not represent a
   *                          {@link ImmutableNode}.
   */
  public static ImmutableNode withHeaderAndChildNodes(final String header, final String... childNodes) {
    final var childNodeContainer = ExtendedIterableView.forArray(childNodes).getViewOf(ImmutableNode::fromString);

    return new ImmutableNode(header, childNodeContainer);
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
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.HEADER);
    }

    return nullableHeader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ImmutableNode> getStoredChildNodes() {
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
  public Node<?> withNewHeader(String header) {
    return withHeaderAndChildNodes(header, getStoredChildNodes());
  }
}
