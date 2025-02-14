package ch.nolix.core.document.chainednode;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link ChainedNode} has the following attributes. -0 or 1 header -an
 * arbitrary number of child {@link ChainedNode}s -0 or 1 next
 * {@link ChainedNode}
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class ChainedNode implements IChainedNode {

  public static final String DOT_CODE = "$D";

  public static final String COMMA_CODE = "$M";

  public static final String DOLLAR_SYMBOL_CODE = "$X";

  public static final String OPEN_BRACKET_CODE = "$O";

  public static final String CLOSED_BRACKET_CODE = "$C";

  private static final String NEXT_NODE_VARIABLE_NAME = "next node";

  private String header;

  private ChainedNode nextNode;

  private final LinkedList<ChainedNode> childNodes = LinkedList.createEmpty();

  /**
   * Creates a new {@link ChainedNode}.
   */
  public ChainedNode() {
    header = null;
    nextNode = null;
  }

  /**
   * Creates a new {@link ChainedNode} with the given header and attributes.
   * 
   * @param header
   * @param attributes
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public ChainedNode(final String header, final Iterable<INode<?>> attributes) {
    setHeader(header);
    addChildNodesFromNodes(attributes);
  }

  /**
   * Creates a new {@link ChainedNode} with the given header, attributes and
   * nextNode.
   * 
   * @param header
   * @param attributes
   * @param nextNode
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if the given nextNode is null.
   */
  public ChainedNode(
    final String header,
    final Iterable<INode<?>> attributes,
    final ChainedNode nextNode) {
    setHeader(header);
    addChildNodesFromNodes(attributes);
    setNextNode(nextNode);
  }

  /**
   * @param node
   * @return a new {@link ChainedNode} from the given node.
   */
  public static ChainedNode fromNode(final INode<?> node) {

    final var chainedNode = new ChainedNode();

    if (node.hasHeader()) {
      chainedNode.setHeader(node.getHeader());
    }

    chainedNode.addChildNodesFromNodes(node.getStoredChildNodes());

    return chainedNode;
  }

  /**
   * @param string
   * @return a new {@link ChainedNode} the given string represents.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link ChainedNode}.
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
  public static String getEscapeStringFor(final String string) {
    return string

      //It is essential to replace the dollar symbol at first.
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

      //It is essential to replace the dollar symbol code at last.
      .replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalog.DOLLAR));
  }

  /**
   * @param childNode
   * @param childNodes
   * @return a new {@link ChainedNode} with the given childNodes.
   * @throws ArgumentIsNullException if one of the given childNodes is null.
   */
  public static ChainedNode withChildNodesFromNodes(final INode<?> childNode, final INode<?>... childNodes) {

    final var chainedNode = new ChainedNode();
    chainedNode.addChildNode(childNode, childNodes);

    return chainedNode;
  }

  /**
   * Creates a new {@link ChainedNode} with the given attributes.
   * 
   * @param attributes
   * @return a new {@link ChainedNode} with the given attributes.
   * @throws ArgumentIsNullException if one of the given attributes is null.
   */
  public static ChainedNode withChildNodesFromNodes(final Iterable<? extends INode<?>> attributes) {

    final var chainedNode = new ChainedNode();
    chainedNode.addChildNodesFromNodes(attributes);

    return chainedNode;
  }

  /**
   * @param header
   * @return a new {@link ChainedNode} with the given header.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static ChainedNode withHeader(final String header) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);

    return chainedNode;
  }

  /**
   * @param header
   * @param attribute
   * @return a new {@link ChainedNode} with the given header and attribute.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if the given attribute is null.
   */
  public static ChainedNode withHeaderAndChildNode(final String header, final ChainedNode attribute) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNode(attribute);

    return chainedNode;
  }

  public static ChainedNode withHeaderAndChildNodes(
    final String header,
    final ChainedNode childNode,
    final ChainedNode... childNodes) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNode(childNode, childNodes);

    return chainedNode;
  }

  /**
   * @param header
   * @param childNode
   * @return a new {@link ChainedNode} with the given header and childNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static ChainedNode withHeaderAndChildNode(final String header, final INode<?> childNode) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNode(childNode);

    return chainedNode;
  }

  /**
   * @param header
   * @param attributes
   * @return a new {@link ChainedNode} with the given header and attributes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if one of the given attribute is null.
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
   * Creates a new {@link ChainedNode} with the given header and attributes.
   * 
   * @param header
   * @param childNode
   * @param childNodes
   * @return a new {@link ChainedNode} with the given header and attributes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if one of the given attributes is null.
   */
  public static ChainedNode withHeaderAndChildNodesFromNodes(
    final String header,
    final INode<?> childNode,
    final INode<?>... childNodes) {

    final var chainedNode = new ChainedNode();
    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes);

    chainedNode.setHeader(header);
    chainedNode.addChildNodesFromNodes(allChildNodes);

    return chainedNode;
  }

  /**
   * Creates a new {@link ChainedNode} with the given header and attributes.
   * 
   * @param header
   * @param attributes
   * @return a new {@link ChainedNode} with the given header and attributes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if one of the given attributes is null.
   */
  public static ChainedNode withHeaderAndChildNodesFromNodes(
    final String header,
    final Iterable<? extends INode<?>> attributes) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNodesFromNodes(attributes);

    return chainedNode;
  }

  /**
   * @param header
   * @param nextNode
   * @return a new {@link ChainedNode} with the given header and nextNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if the given nextNode is null.
   */
  public static ChainedNode withHeaderAndNextNode(final String header, ChainedNode nextNode) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.setNextNode(nextNode);

    return chainedNode;
  }

  /**
   * @param header
   * @param nextNode
   * @param childNode
   * @param childNodes
   * @return a new {@link ChainedNode} with the given header and nextNode.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   * @throws ArgumentIsNullException  if the given nextNode is null.
   * @throws ArgumentIsNullException  if one of the given childNodes is null.
   */
  public static ChainedNode withHeaderAndNextNodeAndChildNodes(
    final String header,
    ChainedNode nextNode,
    final IChainedNode childNode,
    final IChainedNode... childNodes) {

    final var chainedNode = new ChainedNode();
    chainedNode.setHeader(header);
    chainedNode.addChildNode(childNode, childNodes);
    chainedNode.setNextNode(nextNode);

    return chainedNode;
  }

  /**
   * @param chainedNode
   * @return a {@link ChainedNode} from the given chainedNode.
   */
  private static ChainedNode fromChainedNode(final IChainedNode chainedNode) {

    if (chainedNode instanceof final ChainedNode lChainedNode) {
      return lChainedNode;
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
   * {@inheritDoc}
   */
  @Override
  public boolean containsChildNodes() {
    return childNodes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    return (object instanceof final ChainedNode chainedNode && equals(chainedNode));
  }

  /**
   * @return the number of attributes of the current {@link ChainedNode}.
   */
  @Override
  public int getChildNodeCount() {
    return childNodes.getCount();
  }

  /**
   * @param index
   * @return the attribute at the given index of the current {@link ChainedNode}.
   * @throws NonPositiveArgumentException          if the given index is not
   *                                               positive.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               contain an attribute at the
   *                                               given index.
   */
  @Override
  public ChainedNode getChildNodeAt1BasedIndex(final int index) {
    return childNodes.getStoredAt1BasedIndex(index);
  }

  /**
   * @return the attributes of the current {@link ChainedNode}.
   */
  @Override
  public IContainer<ChainedNode> getChildNodes() {
    return childNodes;
  }

  /**
   * @return the {@link Node} representations of the attributes of the current
   *         {@link ChainedNode}.
   * @throws UnrepresentingArgumentException if one of the attributes of the
   *                                         current {@link ChainedNode} does not
   *                                         represent a {@link Node}.
   */
  public IContainer<Node> getChildNodesAsNodes() {
    return childNodes.to(ChainedNode::toNode);
  }

  /**
   * @return a {@link String} representation of the attributes of the current
   *         {@link ChainedNode}.
   */
  public String getChildNodesAsString() {
    return childNodes.toString();
  }

  /**
   * @return the {@link String} representations of the attributes of the current
   *         {@link ChainedNode}.
   */
  public IContainer<String> getChildNodesAsStrings() {
    return childNodes.toStrings();
  }

  /**
   * @return a reproducing {@link String} representation of the header of the
   *         current {@link ChainedNode}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               have a header.
   */
  public String getEscapeHeader() {

    //Asserts that the current ChainedNode has a header.
    if (header == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.HEADER);
    }

    return getEscapeStringFor(header);
  }

  /**
   * @param header
   * @return the first attribute with the given header from the current
   *         {@link ChainedNode}.
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
   * @return the header of the current {@link ChainedNode}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               have a header.
   */
  @Override
  public String getHeader() {

    //Asserts that the current ChainedNode has a header.
    if (header == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.HEADER);
    }

    return header;
  }

  /**
   * @return the next node of the current {@link ChainedNode}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ChainedNode} does not
   *                                               have a next node.
   */
  @Override
  public ChainedNode getNextNode() {

    //Asserts that the current ChanedNode has a next node.
    if (nextNode == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, NEXT_NODE_VARIABLE_NAME);
    }

    return nextNode;
  }

  /**
   * @return the one attribute of the current {@link ChainedNode}.
   * @throws EmptyArgumentException   if the current {@link ChainedNode} does not
   *                                  contain an attribute.
   * @throws InvalidArgumentException if the current {@link ChainedNode} contains
   *                                  several attributes.
   */
  @Override
  public ChainedNode getSingleChildNode() {
    return childNodes.getStoredOne();
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
   * @return a {@link String} representation of the one attribute of the current
   *         {@link ChainedNode}.
   * @throws EmptyArgumentException   if the current {@link ChainedNode} does not
   *                                  contain an attribute.
   * @throws InvalidArgumentException if the current {@link ChainedNode} contains
   *                                  several attributes.
   */
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
    return (header != null);
  }

  /**
   * @return true if the current {@link ChainedNode} has a next node.
   */
  @Override
  public boolean hasNextNode() {
    return (nextNode != null);
  }

  /**
   * @return true if the current {@link ChainedNode} does not have a header and
   *         does not contains attributes.
   */
  @Override
  public boolean isBlank() {
    return !hasHeader()
    && !containsChildNodes();
  }

  /**
   * @return a {@link Double} representation of the current {@link ChainedNode}.
   * @throws UnrepresentingArgumentException if the current {@link ChainedNode}
   *                                         does not represent a {@link Double}.
   */
  @Override
  public double toDouble() {

    //Asserts that the current ChainedNode can represent a Double.
    if (header == null || childNodes.containsAny()) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }

    return Double.valueOf(header);
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
    } catch (final NumberFormatException numberFormatException) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }
  }

  /**
   * A {@link ChainedNode} represents a {@link Node} if: -The {@link ChainedNode}
   * does not have a next node. -Each attribute of the {@link ChainedNode}
   * represents a {@link Node}.
   * 
   * @return a {@link Node} representation of the current {@link ChainedNode}.
   * @throws UnrepresentingArgumentException if the current {@link ChainedNode}
   *                                         does not represent a {@link Node}.
   */
  @Override
  public Node toNode() {

    //Asserts that the current ChainedNode can represent a Node.
    if (nextNode != null) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Node.class);
    }

    //Handles the case that the current ChainedNode does not have a header.
    if (!hasHeader()) {
      return Node.withChildNodes(getChildNodes().to(ChainedNode::toNode));
    }

    //Handles the case that the current ChainedNode has a header.
    return Node.withHeaderAndChildNodes(getHeader(), getChildNodes().to(ChainedNode::toNode));
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
   * Adds the given childNode and childNodes to the current {@link ChainedNode}.
   * 
   * @param childNode
   * @param childNodes
   * @throws ArgumentIsNullException if one of the given childNodes is null.
   */
  private void addChildNode(final IChainedNode childNode, final IChainedNode... childNodes) {

    if (childNode instanceof final ChainedNode chainedNode) {
      this.childNodes.addAtEnd(chainedNode);
    } else {
      this.childNodes.addAtEnd(fromChainedNode(childNode));
    }

    for (final var cn : childNodes) {
      if (cn instanceof final ChainedNode chainedNode) {
        this.childNodes.addAtEnd(chainedNode);
      } else {
        this.childNodes.addAtEnd(fromChainedNode(cn));
      }
    }
  }

  /**
   * Adds the given attributes to the current {@link ChainedNode}.
   * 
   * @param childNode
   * @param childNodes
   */
  private void addChildNode(final INode<?> childNode, final INode<?>... childNodes) {

    this.childNodes.addAtEnd(fromNode(childNode));

    for (final var cn : childNodes) {
      this.childNodes.addAtEnd(fromNode(cn));
    }
  }

  /**
   * Adds the given attributes to the current {@link ChainedNode}.
   * 
   * @param childNodes
   * @throws ArgumentIsNullException if one of the given attribute is null.
   */
  private void addChildNodes(final Iterable<? extends IChainedNode> childNodes) {
    for (final var cn : childNodes) {
      if (cn instanceof final ChainedNode chainedNode) {
        this.childNodes.addAtEnd(chainedNode);
      } else {
        this.childNodes.addAtEnd(fromChainedNode(cn));
      }
    }
  }

  /**
   * Adds the given attributes to the current {@link ChainedNode}.
   * 
   * @param attributes
   */
  private void addChildNodesFromNodes(final Iterable<? extends INode<?>> attributes) {
    for (final var a : attributes) {
      this.childNodes.addAtEnd(fromNode(a));
    }
  }

  /**
   * Appends the {@link String} representation of the current {@link ChainedNode}
   * to the given stringBuilder.
   * 
   * @param stringBuilder
   */
  private void appendStringRepresentationTo(final StringBuilder stringBuilder) {

    //Handles the case that the current ChainedNode has a header.
    if (header != null) {
      stringBuilder.append(getEscapeStringFor(header));
    }

    //Handles the case that the current ChainedNode contains attributes.
    if (childNodes.containsAny()) {

      stringBuilder.append("(");

      var atBegin = true;
      for (final var a : childNodes) {

        if (atBegin) {
          atBegin = false;
        } else {
          stringBuilder.append(",");
        }

        a.appendStringRepresentationTo(stringBuilder);
      }

      stringBuilder.append(")");
    }

    //Handles the case that the current ChainedNode contains a next node.
    if (nextNode != null) {
      stringBuilder.append(".");
      nextNode.appendStringRepresentationTo(stringBuilder);
    }
  }

  private int calculateNextIndexFromStartIndexAndHeaderLengthAndTaskAfterSetHeader(
    final int startIndex,
    final int headerLength,
    final TaskAfterSetHeader taskAfterSetHeader) {
    var nextIndex = startIndex + headerLength;
    if (taskAfterSetHeader == TaskAfterSetHeader.MAP_CHILD_NODES_AND_POTENTIAL_NEXT_NODE
    || taskAfterSetHeader == TaskAfterSetHeader.MAP_NEXT_NODE) {
      nextIndex++;
    }
    return nextIndex;
  }

  /**
   * @param chainedNode
   * @return true if the current {@link ChainedNode} equals the given chainedNode.
   */
  private boolean equals(final ChainedNode chainedNode) {
    return canEqualBecauseOfHeader(chainedNode)
    && canEqualBecauseOfChildNodes(chainedNode);
  }

  /**
   * @param chainedNode
   * @return true if the current {@link ChainedNode} can equal the given
   *         chainedNode because of the attributes.
   */
  private boolean canEqualBecauseOfChildNodes(final ChainedNode chainedNode) {

    if (getChildNodeCount() != chainedNode.getChildNodeCount()) {
      return false;
    }

    var i = 1;
    for (final var a : getChildNodes()) {

      if (!a.equals(chainedNode.getChildNodeAt1BasedIndex(i))) {
        return false;
      }

      i++;
    }

    if (!hasNextNode()) {
      if (chainedNode.hasNextNode()) {
        return false;
      }
    } else {
      if (!chainedNode.hasNextNode()) {
        return false;
      }
      if (!getNextNode().equals(chainedNode.getNextNode())) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param chainedNode
   * @return true if the current {@link ChainedNode} can equal the given
   *         chainedNode because of the header.
   */
  private boolean canEqualBecauseOfHeader(final ChainedNode chainedNode) {

    if (!hasHeader()) {
      return !chainedNode.hasHeader();
    }

    return (chainedNode.hasHeader() && hasHeader(chainedNode.getHeader()));
  }

  /**
   * @param string
   * @param startIndex
   * @return the length of the probable header of the {@link ChainedNode} the
   *         given string represents starting from the given startIndex.
   */
  private HeaderLengthAndTaskAfterSetHeaderParameter getHeaderLengthAndTaskAfterSetHeader(
    final String string,
    final int startIndex) {

    var nextIndex = startIndex;
    while (nextIndex < string.length()) {
      switch (string.charAt(nextIndex)) {
        case CharacterCatalog.OPEN_BRACKET:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(
            nextIndex - startIndex,
            TaskAfterSetHeader.MAP_CHILD_NODES_AND_POTENTIAL_NEXT_NODE);
        case CharacterCatalog.COMMA:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
        case CharacterCatalog.CLOSED_BRACKET:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
        case CharacterCatalog.DOT:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex,
            TaskAfterSetHeader.MAP_NEXT_NODE);
        default:
          nextIndex++;
      }
    }

    return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
  }

  private int mapChildNodesAndPotentialNextNodeFromStingAndStartIndexAndGetNextIndex(
    final String string,
    final int startIndex) {

    var nextIndex = startIndex;

    final var node = new ChainedNode();
    nextIndex = node.setFromStringAndStartIndexAndGetNextIndex(string, nextIndex);
    this.childNodes.addAtEnd(node);

    while (nextIndex < string.length()) {

      final var character = string.charAt(nextIndex);

      if (character == ',') {
        final var node2 = new ChainedNode();
        nextIndex = node2.setFromStringAndStartIndexAndGetNextIndex(string, nextIndex + 1);
        this.childNodes.addAtEnd(node2);
      } else if (character == ')') {
        nextIndex++;
        break;
      } else {
        //Does nothing and continues the current loop.
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
    header = null;
    childNodes.clear();
    nextNode = null;
  }

  /**
   * Resets the current {@link ChainedNode} from the given string.
   * 
   * @param string
   * @throws UnrepresentingArgumentException if the given string does nor
   *                                         represent a {@link ChainedNode}.
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

    final var headerLengthAndTaskAfterSetHeader = getHeaderLengthAndTaskAfterSetHeader(string, startIndex);
    final var headerLength = headerLengthAndTaskAfterSetHeader.getHeaderLength();
    final var taskAfterSetHeader = headerLengthAndTaskAfterSetHeader.getTaskAfterSetHeader();

    setPotentialHeaderFromStringAndStartIndexAndHeaderLength(string, startIndex, headerLength);

    var nextIndex = calculateNextIndexFromStartIndexAndHeaderLengthAndTaskAfterSetHeader(
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
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private void setHeader(final String header) {

    //Asserts that the given header is not null.
    if (header == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.HEADER);
    }

    //Asserts that the given header is not blank.
    if (header.isBlank()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalog.HEADER,
        header,
        "is blank");
    }

    this.header = header;
  }

  /**
   * Sets the next node of the current {@link ChainedNode}.
   * 
   * @param nextNode
   * @throws ArgumentIsNullException if the given nextNode is null.
   */
  private void setNextNode(final IChainedNode nextNode) {

    //Asserts that the given nextNode is not null.
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
      header = getStoredginStringFromEscapeString(string.substring(startIndex, startIndex + headerLength));
    }
  }
}
