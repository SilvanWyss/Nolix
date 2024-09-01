//package declaration
package ch.nolix.core.document.node;

import ch.nolix.core.container.containerview.ContainerView;
//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A {@link Node} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class Node extends BaseNode<Node> {

  //constant
  public static final Node EMPTY_NODE = new Node();

  //optional attribute
  private final String header;

  //multi-attribute
  private final ImmutableList<Node> childNodes;

  //constructor
  private Node() {

    header = null;

    childNodes = ImmutableList.forIterable(LinkedList.createEmpty());
  }

  //constructor
  /**
   * Creates a new {@link Node} with the given childNodes.
   * 
   * @param childNodes
   */
  private Node(final Iterable<? extends INode<?>> childNodes) {

    header = null;

    this.childNodes = ImmutableList.forIterable(createNodesFromNodes(childNodes));
  }

  //constructor
  /**
   * Creates a new {@link Node} with the given header.
   * 
   * @param header
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private Node(final String header) {

    this.header = getValidHeaderFromHeader(header);

    childNodes = ImmutableList.createEmpty();
  }

  //constructor
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

    this.header = getValidHeaderFromHeader(header);

    this.childNodes = //
    ImmutableList.forIterable(createNodesFromNodes(ContainerView.forElementAndArray(childNode, childNodes)));
  }

  //constructor
  /**
   * Creates a new {@link Node} with the given header and childNodes.
   * 
   * @param header
   * @param childNodes
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private Node(final String header, final Iterable<? extends INode<?>> childNodes) {

    this.header = getValidHeaderFromHeader(header);

    this.childNodes = ImmutableList.forIterable(createNodesFromNodes(childNodes));
  }

  //static method
  /**
   * @param pEnum
   * @return a new {@link Node} from the given pEnum.
   */
  public static Node fromEnum(final Enum<?> pEnum) {
    return withHeaderAndChildNode(getTypeNameOfEnum(pEnum), withHeader(pEnum.name()));
  }

  //static method
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

  //static method
  /**
   * @param node
   * @return a new {@link Node} from the given {@link INode}.
   */
  public static Node fromNode(final INode<?> node) {

    if (node instanceof Node lNode) {
      return lNode;
    }

    if (!node.hasHeader()) {
      return withChildNodes(node.getStoredChildNodes());
    }

    return withHeaderAndChildNodes(node.getHeader(), node.getStoredChildNodes());
  }

  //static method
  /**
   * @param string
   * @return a new {@link Node} from the given string.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link Node}.
   */
  public static Node fromString(final String string) {
    return fromNode(MutableNode.fromString(string));
  }

  //static method
  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final boolean childNode) {
    return withChildNode(withHeader(childNode));
  }

  //static method
  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final double childNode) {
    return withChildNode(withHeader(childNode));
  }

  //static method
  /**
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   */
  public static Node withChildNode(final INode<?> childNode, final INode<?>... childNodes) {

    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes);

    return new Node(allChildNodes);
  }

  //static method
  /**
   * @param childNode
   * @return a new {@link Node} with the given childNode.
   */
  public static Node withChildNode(final long childNode) {
    return withChildNode(withHeader(childNode));
  }

  //static method
  /**
   * @param childNode
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withChildNode(final String childNode, final String... childNodes) {

    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes).to(Node::withHeader);

    return withChildNodes(allChildNodes);
  }

  //static method
  /**
   * @param childNodes
   * @return a new {@link Node} with the given childNodes.
   */
  public static Node withChildNodes(final Iterable<? extends INode<?>> childNodes) {
    return new Node(childNodes);
  }

  //static method
  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final boolean header) {
    return withHeader(String.valueOf(header));
  }

  //static method
  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final double header) {
    return withHeader(String.valueOf(header));
  }

  //static method
  /**
   * @param header
   * @return a new {@link Node} with the given header.
   */
  public static Node withHeader(final long header) {
    return withHeader(String.valueOf(header));
  }

  //static method
  /**
   * @param header
   * @return a new {@link Node} with the given header.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  public static Node withHeader(final String header) {
    return new Node(header);
  }

  //static method
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

  //static method
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

  //static method
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

  //static method
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

  //static method
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

  //static method
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

    final var allChildNodes = ContainerView.forElementAndArray(childNode, childNodes).to(Node::withHeader);

    return withHeaderAndChildNodes(header, allChildNodes);
  }

  //static method
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

  //static method
  /**
   * @param pEnum
   * @return the name of the type of the given pEnum.
   */
  private static String getTypeNameOfEnum(final Enum<?> pEnum) {
    return pEnum.getClass().getSimpleName();
  }

  //static method
  /**
   * @param header
   * @return a valid header from the given header.
   * @throws ArgumentIsNullException  if the given header is null.
   * @throws InvalidArgumentException if the given header is blank.
   */
  private static String getValidHeaderFromHeader(final String header) {

    GlobalValidator.assertThat(header).thatIsNamed(LowerCaseVariableCatalogue.HEADER).isNotBlank();

    return header;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> asWithHeader(String header) {
    return withHeaderAndChildNodes(header, getStoredChildNodes());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {

    if (!(object instanceof Node)) {
      return false;
    }

    return equalsNode((Node) object);
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {

    if (header == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.HEADER);
    }

    return header;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<Node> getStoredChildNodes() {
    return childNodes;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasHeader() {
    return (header != null);
  }
}
