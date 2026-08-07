/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.chainednode;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link ChainedNode} has the following attributes. -0 or 1 header -an
 * arbitrary number of child {@link ChainedNode}s -0 or 1 next
 * {@link ChainedNode}
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ChainedNode // NOSONAR: A ChainedNode is a principal object thus it has many methods.
implements IChainedNode {
  public static final ChainedNode EMPTY_CHAINED_NODE = new ChainedNode();

  public static final String DOT_CODE = "$D";

  public static final String COMMA_CODE = "$M";

  public static final String DOLLAR_SYMBOL_CODE = "$X";

  public static final String OPEN_BRACKET_CODE = "$O";

  public static final String CLOSED_BRACKET_CODE = "$C";

  private static final String NEXT_NODE_VARIABLE_NAME = "next node";

  private String memberHeader;

  private ChainedNode nextNode;

  private final LinkedList<ChainedNode> memberChildNodes = LinkedList.createEmpty();

  /**
   * Creates a new empty {@link ChainedNode}.
   */
  private ChainedNode() {
    memberHeader = null;
    nextNode = null;
  }

  /**
   * @param chainedNode
   * @return a {@link ChainedNode} from the given chainedNode
   * @throws RuntimeException if the given chainedNode is null
   */
  public static ChainedNode fromChainedNode(final IChainedNode chainedNode) {
    if (chainedNode instanceof final ChainedNode localChainedNode) {
      return localChainedNode;
    }

    final var newChainedNode = new ChainedNode();

    if (chainedNode.hasHeader()) {
      newChainedNode.setHeader(chainedNode.getHeader());
    }

    newChainedNode.addChildNodes(chainedNode.getChildNodes());

    if (chainedNode.hasNextNode()) {
      newChainedNode.setNextNode(chainedNode.getNextNode());
    }

    return newChainedNode;
  }

  /**
   * @param node
   * @return a new {@link ChainedNode} from the given node.
   */
  public static ChainedNode fromNode(final Node<?> node) {
    final var chainedNode = new ChainedNode();

    if (node.hasHeader()) {
      chainedNode.setHeader(node.getHeader());
    }

    chainedNode.addChildNodesFromNodes(node.getStoredChildNodes());

    return chainedNode;
  }

  /**
   * @param string
   * @return a new {@link ChainedNode} the given string represents
   * @throws RuntimeException if the given string does not represent a
   *                          {@link ChainedNode}.
   */
  public static ChainedNode fromString(final String string) {
    final var chainedNode = new ChainedNode();
    chainedNode.resetFromString(string);

    return chainedNode;
  }

  /**
   * @param string
   * @return an escape {@link String} for the given string.
   */
  public static String getEscapeStringForString(final String string) {
    return string

      // It is essential to replace the dollar symbol at first.
      .replace(String.valueOf(CharacterCatalog.DOLLAR), DOLLAR_SYMBOL_CODE)

      .replace(String.valueOf(CharacterCatalog.DOT), DOT_CODE)
      .replace(String.valueOf(CharacterCatalog.COMMA), COMMA_CODE)
      .replace(String.valueOf(CharacterCatalog.OPEN_BRACKET), OPEN_BRACKET_CODE)
      .replace(String.valueOf(CharacterCatalog.CLOSED_BRACKET), CLOSED_BRACKET_CODE);
  }

  /**
   * @param escapeString
   * @return an origin {@link String} from the given escapeString.
   */
  public static String getStoredginStringFromEscapeString(final String escapeString) {
    return escapeString
      .replace(DOT_CODE, String.valueOf(CharacterCatalog.DOT))
      .replace(COMMA_CODE, String.valueOf(CharacterCatalog.COMMA))
      .replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalog.OPEN_BRACKET))
      .replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalog.CLOSED_BRACKET))

      // It is essential to replace the dollar symbol code at last.
      .replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalog.DOLLAR));
  }

  /**
   * @param nodes
   * @return a new {@link ChainedNode} with the childNodes
   * @throws RuntimeException if the given nodes is null
   * @throws RuntimeException if one of the given nodes is null
   */
  public static ChainedNode withChildNodes(final Node<?>... nodes) {
    final var chainedNode = new ChainedNode();

    chainedNode.addChildNodes(nodes);

    return chainedNode;
  }

  /**
   * Creates a new {@link ChainedNode} with the given attributes.
   * 
   * @param attributes
   * @return a new {@link ChainedNode} with the given attributes
   * @throws RuntimeException if one of the given attributes is null
   */
  public static ChainedNode withChildNodesFromNodes(final Iterable<? extends Node<?>> attributes) {
    final var chainedNode = new ChainedNode();
    chainedNode.addChildNodesFromNodes(attributes);

    return chainedNode;
  }

  /**
   * @param header
   * @return a new {@link ChainedNode} with the given header
   * @throws RuntimeException if the given header is null
   * @throws RuntimeException if the given header is blank
   */
  public static ChainedNode withHeader(final String header) {
    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);

    return chainedNode;
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ChainedNode} with the given header and childNode
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNode is null
   */
  public static ChainedNode withHeaderAndChildNode(final String header, final IChainedNode childNode) {
    final var chainedNode = new ChainedNode();

    chainedNode.setHeader(header);
    chainedNode.addChildNodes(childNode);

    return chainedNode;
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link ChainedNode} with the given header and childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given header is blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ChainedNode withHeaderAndChildNodes(final String header, final IChainedNode... childNodes) {
    final var chainedNode = new ChainedNode();

    chainedNode.setHeader(header);
    chainedNode.addChildNodes(childNodes);

    return chainedNode;
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ChainedNode} with the given header and childNode
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given header is blank
   */
  public static ChainedNode withHeaderAndChildNode(final String header, final Node<?> childNode) {
    final var chainedNode = new ChainedNode();

    chainedNode.setHeader(header);
    chainedNode.addChildNode(childNode);

    return chainedNode;
  }

  /**
   * @param header
   * @param attributes
   * @return a new {@link ChainedNode} with the given header and attributes
   * @throws RuntimeException if the given header is null
   * @throws RuntimeException if the given header is blank
   * @throws RuntimeException if one of the given attribute is null
   */
  public static ChainedNode withHeaderAndChildNodes(
    final String header,
    final Iterable<? extends IChainedNode> attributes) {
    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNodes(attributes);

    return chainedNode;
  }

  /**
   * @param header
   * @param childNodes
   * @return a new {@link ChainedNode} with the given header and childNodes
   * @throws RuntimeException if the given header is null or blank
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  public static ChainedNode withHeaderAndChildNodes(final String header, final Node<?>... childNodes) {
    final var chainedNode = new ChainedNode();

    chainedNode.setHeader(header);
    chainedNode.addChildNodes(childNodes);

    return chainedNode;
  }

  /**
   * Creates a new {@link ChainedNode} with the given header and attributes.
   * 
   * @param header
   * @param attributes
   * @return a new {@link ChainedNode} with the given header and attributes
   * @throws RuntimeException if the given header is null
   * @throws RuntimeException if the given header is blank
   * @throws RuntimeException if one of the given attributes is null
   */
  public static ChainedNode withHeaderAndChildNodesFromNodes(
    final String header,
    final Iterable<? extends Node<?>> attributes) {
    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNodesFromNodes(attributes);

    return chainedNode;
  }

  /**
   * @param header
   * @param nextNode
   * @return a new {@link ChainedNode} with the given header and nextNode
   * @throws RuntimeException if the given header is null
   * @throws RuntimeException if the given header is blank
   * @throws RuntimeException if the given nextNode is null
   */
  public static ChainedNode withHeaderAndNextNode(final String header, ChainedNode nextNode) {
    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.setNextNode(nextNode);

    return chainedNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsChildNodes() {
    return memberChildNodes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    return //
    object instanceof final ChainedNode chainedNode
    && ChainedNodeComparator.areEqual(this, chainedNode);
  }

  /**
   * @return the number of attributes of the current {@link ChainedNode}.
   */
  @Override
  public int getChildNodeCount() {
    return memberChildNodes.getCount();
  }

  /**
   * @param index
   * @return the attribute at the given index of the current {@link ChainedNode}
   * @throws RuntimeException                      if the given index is not
   *                                               positive
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               contain an attribute at the
   *                                               given index.
   */
  @Override
  public ChainedNode getChildNodeAtOneBasedIndex(final int index) {
    return memberChildNodes.getStoredAtOneBasedIndex(index);
  }

  /**
   * @return the attributes of the current {@link ChainedNode}.
   */
  @Override
  public ExtendedIterable<ChainedNode> getChildNodes() {
    return memberChildNodes;
  }

  /**
   * @param header
   * @return the first attribute with the given header from the current
   *         {@link ChainedNode}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               contain an attribute with the
   *                                               given header.
   */
  @Override
  public ChainedNode getFirstChildNodeWithHeader(final String header) {
    return getChildNodes().getStoredFirst(a -> a.hasHeader(header));
  }

  /**
   * @return the header of the current {@link ChainedNode}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               have a header.
   */
  @Override
  public String getHeader() {
    // Asserts that the current ChainedNode has a header.
    if (memberHeader == null) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.HEADER);
    }

    return memberHeader;
  }

  /**
   * @return the next node of the current {@link ChainedNode}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               have a next node.
   */
  @Override
  public ChainedNode getNextNode() {
    // Asserts that the current ChanedNode has a next node.
    if (nextNode == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, NEXT_NODE_VARIABLE_NAME);
    }

    return nextNode;
  }

  /**
   * @return the one attribute of the current {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          an attribute
   * @throws RuntimeException if the current {@link ChainedNode} contains several
   *                          attributes.
   */
  @Override
  public ChainedNode getSingleChildNode() {
    return memberChildNodes.getStoredSingle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSingleChildNodeHeader() {
    return getSingleChildNode().getHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSingleChildNodeAsDouble() {
    return getSingleChildNode().toDouble();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSingleChildNodeAsInt() {
    return getSingleChildNode().toInt();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSingleChildNodeAsString() {
    return getSingleChildNode().toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
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
  public boolean hasNextNode() {
    return (nextNode != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBlank() {
    return !hasHeader()
    && !containsChildNodes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double toDouble() {
    // Asserts that the current ChainedNode can represent a Double.
    if (memberHeader == null || memberChildNodes.containsAny()) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }

    return Double.valueOf(memberHeader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int toInt() {
    if (!hasHeader() || containsChildNodes() || hasNextNode()) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }

    try {
      return Integer.parseInt(getHeader());
    } catch (final NumberFormatException _) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImmutableNode toNode() {
    // Asserts that the current ChainedNode can represent a Node.
    if (nextNode != null) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Node.class);
    }

    // Handles the case that the current ChainedNode does not have a header.
    if (!hasHeader()) {
      return ImmutableNode.withChildNodes(getChildNodes().getViewOf(ChainedNode::toNode));
    }

    // Handles the case that the current ChainedNode has a header.
    return ImmutableNode.withHeaderAndChildNodes(getHeader(), getChildNodes().getViewOf(ChainedNode::toNode));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final var stringBuilder = new StringBuilder();
    appendStringRepresentationTo(stringBuilder);
    return stringBuilder.toString();
  }

  /**
   * Adds the given childNode to the current {@link ChainedNode}.
   * 
   * @param childNode
   * @throws RuntimeException if the given childNode is null
   */
  private void addChildNode(final Node<?> childNode) {
    memberChildNodes.addAtEnd(fromNode(childNode));
  }

  /**
   * Adds the given childNodes to the current {@link ChainedNode}.
   * 
   * @param childNodes
   * @throws RuntimeException the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  private void addChildNodes(final IChainedNode... childNodes) {
    for (final var c : childNodes) {
      final var childNode = fromChainedNode(c);

      memberChildNodes.addAtEnd(childNode);
    }
  }

  /**
   * Adds the given childNodes to the current {@link ChainedNode}.
   * 
   * @param childNodes
   * @throws RuntimeException if the given childNodes is null
   * @throws RuntimeException if one of the given childNodes is null
   */
  private void addChildNodes(final Node<?>... childNodes) {
    for (final var c : childNodes) {
      memberChildNodes.addAtEnd(fromNode(c));
    }
  }

  /**
   * Adds the given attributes to the current {@link ChainedNode}.
   * 
   * @param childNodes
   * @throws RuntimeException if one of the given attribute is null
   */
  private void addChildNodes(final Iterable<? extends IChainedNode> childNodes) {
    for (final var c : childNodes) {
      if (c instanceof final ChainedNode chainedNode) {
        memberChildNodes.addAtEnd(chainedNode);
      } else {
        memberChildNodes.addAtEnd(fromChainedNode(c));
      }
    }
  }

  /**
   * Adds the given attributes to the current {@link ChainedNode}.
   * 
   * @param attributes
   */
  private void addChildNodesFromNodes(final Iterable<? extends Node<?>> attributes) {
    for (final var a : attributes) {
      memberChildNodes.addAtEnd(fromNode(a));
    }
  }

  /**
   * Appends the {@link String} representation of the current {@link ChainedNode}
   * to the given stringBuilder.
   * 
   * @param stringBuilder
   */
  private void appendStringRepresentationTo(final StringBuilder stringBuilder) {
    // Handles the case that the current ChainedNode has a header.
    if (memberHeader != null) {
      stringBuilder.append(getEscapeStringForString(memberHeader));
    }

    // Handles the case that the current ChainedNode contains attributes.
    if (memberChildNodes.containsAny()) {
      stringBuilder.append("(");

      var atBegin = true;
      for (final var a : memberChildNodes) {
        if (atBegin) {
          atBegin = false;
        } else {
          stringBuilder.append(",");
        }

        a.appendStringRepresentationTo(stringBuilder);
      }

      stringBuilder.append(")");
    }

    // Handles the case that the current ChainedNode contains a next node.
    if (nextNode != null) {
      stringBuilder.append(".");
      nextNode.appendStringRepresentationTo(stringBuilder);
    }
  }

  private int mapChildNodesAndPotentialNextNodeFromStingAndStartIndexAndGetNextIndex(
    final String string,
    final int startIndex) {
    var nextIndex = startIndex;

    final var node = new ChainedNode();
    nextIndex = node.setFromStringAndStartIndexAndGetNextIndex(string, nextIndex);
    memberChildNodes.addAtEnd(node);

    while (nextIndex < string.length()) {
      final var character = string.charAt(nextIndex);

      if (character == ',') {
        final var node2 = new ChainedNode();
        nextIndex = node2.setFromStringAndStartIndexAndGetNextIndex(string, nextIndex + 1);
        memberChildNodes.addAtEnd(node2);
      } else if (character == ')') {
        nextIndex++;
        break;
      } else {
        // Does nothing and continues the current loop.
      }
    }

    if (nextIndex < string.length() - 1 && string.charAt(nextIndex) == '.') {
      nextIndex++;
      return mapNextNodeFromStringAndStartIndexAndGetNextIndex(string, nextIndex);
    }

    return nextIndex;
  }

  private int mapNextNodeFromStringAndStartIndexAndGetNextIndex(final String string, final int startIndex) {
    nextNode = new ChainedNode();

    return nextNode.setFromStringAndStartIndexAndGetNextIndex(string, startIndex);
  }

  /**
   * Resets the current {@link ChainedNode}.
   */
  private void reset() {
    memberHeader = null;
    memberChildNodes.clear();
    nextNode = null;
  }

  /**
   * Resets the current {@link ChainedNode} from the given string.
   * 
   * @param string
   * @throws RuntimeException if the given string does nor represent a
   *                          {@link ChainedNode}.
   */
  private void resetFromString(final String string) {
    reset();

    if (setFromStringAndStartIndexAndGetNextIndex(string, 0) != string.length()) {
      reset();

      throw UnrepresentingArgumentException.forArgumentAndType(string, ChainedNode.class);
    }
  }

  /**
   * Sets the current {@link ChainedNode} from the given string starting from the
   * given startIndex. The given startIndex and the returned next index are
   * zero-based.
   * 
   * @param string
   * @param startIndex
   * @return the next index the given string can be processed from.
   */
  private int setFromStringAndStartIndexAndGetNextIndex(final String string, final int startIndex) {
    final var headerLengthAndTaskAfterSetHeader = //
    ChainedNodeStringHelper.getHeaderLengthAndTaskAfterSetHeader(string, startIndex);

    final var headerLength = headerLengthAndTaskAfterSetHeader.getHeaderLength();
    final var taskAfterSetHeader = headerLengthAndTaskAfterSetHeader.getTaskAfterSetHeader();

    setPotentialHeaderFromStringAndStartIndexAndHeaderLength(string, startIndex, headerLength);

    var nextIndex = ChainedNodeStringHelper.calculateNextIndexFromStartIndexAndHeaderLengthAndTaskAfterSetHeader(
      startIndex,
      headerLength,
      taskAfterSetHeader);

    return switch (taskAfterSetHeader) {
      case DO_NOTHING ->
        nextIndex;
      case MAP_NEXT_NODE ->
        mapNextNodeFromStringAndStartIndexAndGetNextIndex(string, nextIndex);
      case MAP_CHILD_NODES_AND_POTENTIAL_NEXT_NODE ->
        mapChildNodesAndPotentialNextNodeFromStingAndStartIndexAndGetNextIndex(string, nextIndex);
    };
  }

  /**
   * Sets the header of the current {@link ChainedNode}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null
   * @throws RuntimeException if the given header is blank
   */
  private void setHeader(final String header) {
    // Asserts that the given header is not null.
    if (header == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.HEADER);
    }

    // Asserts that the given header is not blank.
    if (header.isBlank()) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        LowerCaseVariableNameCatalog.HEADER,
        header,
        "is blank");
    }

    memberHeader = header;
  }

  /**
   * Sets the next node of the current {@link ChainedNode}.
   * 
   * @param nextNode
   * @throws RuntimeException if the given nextNode is null
   */
  private void setNextNode(final IChainedNode nextNode) {
    // Asserts that the given nextNode is not null.
    if (nextNode == null) {
      throw ArgumentIsNullException.forArgumentName(NEXT_NODE_VARIABLE_NAME);
    }

    if (nextNode instanceof final ChainedNode chainedNode) {
      this.nextNode = chainedNode;
    } else {
      this.nextNode = fromChainedNode(nextNode);
    }
  }

  /**
   * Sets the probable header of the current {@link ChainedNode}. The header is in
   * the given string starting from the given startIndex and has the given
   * headerLength.
   * 
   * @param string
   * @param startIndex
   * @param headerLength
   */
  private void setPotentialHeaderFromStringAndStartIndexAndHeaderLength(
    final String string,
    final int startIndex,
    final int headerLength) {
    if (headerLength > 0) {
      memberHeader = getStoredginStringFromEscapeString(string.substring(startIndex, startIndex + headerLength));
    }
  }
}
