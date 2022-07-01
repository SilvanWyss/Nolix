//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class Node extends BaseNode<Node> {
	
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
	 * @throws InvalidArgumentException if the given filePath is not valid.
	 * @throws UnrepresentingArgumentException if the file with the given filePath does not represent a {@link Node}.
	 */
	public static Node fromFile(final String filePath) {
		return fromNode(MutableNode.fromFile(filePath));
	}
	
	//static method
	/**
	 * @param intPair
	 * @return a new {@link Node} from the given intPair.
	 */
	public static Node fromIntPair(final IntPair intPair) {
		return withChildNode(withHeader(intPair.getValue1()), withHeader(intPair.getValue2()));
	}
	
	//static method
	/**
	 * @param node
	 * @return a new {@link Node} from the given {@link INode}.
	 */
	public static Node fromNode(final INode<?> node) {
		
		if (!node.hasHeader()) {
			return withChildNodes(node.getRefChildNodes());
		}
		
		return withHeaderAndChildNodes(node.getHeader(), node.getRefChildNodes());
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link Node} from the given string.
	 * @throws UnrepresentingArgumentException if the given string does not represent a {@link Node}.
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
	 * @param childNodes
	 * @return a new {@link Node} with the given childNodes.
	 */
	public static Node withChildNode(final INode<?>... childNodes) {
		return new Node(childNodes);
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
	 * @param childNodes
	 * @return a new {@link Node} with the given childNodes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withChildNode(final String... childNodes) {
		return withChildNodes(ReadContainer.forArray(childNodes).to(Node::withHeader));
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
	 * @throws ArgumentIsNullException if the given header is null.
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
	 * @throws ArgumentIsNullException if the given header is null.
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
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndChildNode(final String header, final double childNode) {
		return withHeaderAndChildNode(header, withHeader(childNode));
	}
	
	//static method
	/**
	 * @param header
	 * @param childNodes
	 * @return a new {@link Node} with the given header and childNodes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndChildNode(final String header, final INode<?>... childNodes) {
		return new Node(header, childNodes);
	}
	
	//static method
	/**
	 * @param header
	 * @param childNodes
	 * @return a new {@link Node} with the given childNodes.
	 * @throws ArgumentIsNullException if the given header is null.
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
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndChildNode(final String header, final long childNode) {
		return withHeaderAndChildNode(header, withHeader(childNode));
	}
	
	//static method
	/**
	 * @param header
	 * @param childNodes
	 * @return a new {@link Node} with the given header and childNodes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given childNodes is null.
	 * @throws InvalidArgumentException if one of the given childNodes is blank.
	 */
	public static Node withHeaderAndChildNode(final String header, final String... childNodes) {
		return withHeaderAndChildNodes(header, ReadContainer.forArray(childNodes).to(Node::withHeader));
	}
	
	//static method
	/**
	 * @param nodes
	 * @return new {@link Node}s from the given nodes.
	 * @throws RuntimeException if one of the given nodes is null.
	 */
	private static IContainer<Node> createNodesFromNodes(final INode<?>[] nodes) {
		
		final var lNodes = new LinkedList<Node>();
		
		for (final var n : nodes) {
			lNodes.addAtEnd(fromNode(n));
		}
		
		return lNodes;
	}
	
	//static method
	/**
	 * @param nodes
	 * @return new {@link Node}s from the given nodes.
	 * @throws RuntimeException if one of the given nodes is null.
	 */
	private static IContainer<Node> createNodesFromNodes(final Iterable<? extends INode<?>> nodes) {
		
		final var lNodes = new LinkedList<Node>();
		
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
	 * @return a valid header from the given header.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private static String getValidHeaderFromHeader(final String header) {
		
		GlobalValidator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		return header;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private LinkedList<Node> childNodes = new LinkedList<>();
	
	//TODO: Replace this constructor by Node.EMPTY_NODE constant.
	public Node() {}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given childNodes.
	 * 
	 * @param childNodes
	 */
	private Node(final INode<?>[] childNodes) {
		
		header = null;
		
		//TODO: Remove cast.
		this.childNodes = (LinkedList<Node>)createNodesFromNodes(childNodes);
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given childNodes.
	 * 
	 * @param childNodes
	 */
	private Node(final Iterable<? extends INode<?>> childNodes) {
		
		header = null;
		
		//TODO: Remove cast.
		this.childNodes = (LinkedList<Node>)createNodesFromNodes(childNodes);
	}

	//constructor
	/**
	 * Creates a new {@link Node} with the given header.
	 * 
	 * @param header
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private Node(final String header) {
		
		this.header = getValidHeaderFromHeader(header);
		
		childNodes = new LinkedList<>();
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header and childNodes.
	 * 
	 * @param header
	 * @param childNodes
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private Node(final String header, final INode<?>[] childNodes) {
		
		this.header = getValidHeaderFromHeader(header);
		
		//TODO: Remove cast.
		this.childNodes = (LinkedList<Node>)createNodesFromNodes(childNodes);
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header and childNodes.
	 * 
	 * @param header
	 * @param childNodes
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private Node(final String header, final Iterable<? extends INode<?>> childNodes) {
		
		this.header = getValidHeaderFromHeader(header);
		
		//TODO: Remove cast.
		this.childNodes = (LinkedList<Node>)createNodesFromNodes(childNodes);
	}
	
	//method
	public Node addChildNode(final INode<?>... childNodes) {
		
		for (final var cn : childNodes) {
			this.childNodes.addAtEnd(Node.fromNode(cn));
		}
		
		return this;
	}
	
	//method
	public Node addChildNodeFromString(final String... strings) {
		
		for (final var s : strings) {
			addChildNode(fromString(s));
		}
		
		return this;
	}
	
	//method
	@Override
	public <N extends INode<?>> Node addChildNodes(final Iterable<N> childNodes) {
		
		for (final var cn : childNodes) {
			this.childNodes.addAtEnd(Node.fromNode(cn));
		}
		
		return this;
	}
	
	//method
	public Node addChildNodesFromStrings(final Iterable<String> strings) {
		
		for (final var s : strings) {
			addChildNodeFromString(s);
		}
		
		return this;
	}
	
	//method
	/**
	 * @return the header of this specification.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Node} does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Asserts that the current Node has a header.
		//For a better performance, this implementation does not use all comfortable methods.
		if (header == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.HEADER);
		}
		
		return header;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<Node> getRefChildNodes() {
		return ReadContainer.forIterable(childNodes).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getRefSingleChildNode() {
		return childNodes.getRefOne();
	}
	
	//method
	/**
	 * @return true if the current {@link Node} has a header
	 */
	@Override
	public boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Removes the attributes of the current {@link Node}.
	 */
	@Override
	public void removeChildNodes() {
		childNodes.clear();
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link Node}.
	 * 
	 * @param selector
	 * @return the first attribute the given selector selects.
	 * @throws InvalidArgumentException if
	 * the current {@link Node} does not contain an attribute the given selector selects.
	 */
	@Override
	public Node removeAndGetRefFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return childNodes.removeAndGetRefFirst(selector::getOutput);
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link Node}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if the current {@link Node} does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		childNodes.removeFirst(selector::getOutput);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeHeader() {
		header = null;
	}
	
	//method
	/**
	 * Sets the header of the current {@link Node}.
	 * 
	 * @param header
	 * @return the current {@link Node}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public Node setHeader(final String header) {
		
		//Asserts that the given header is not null or blank.
		GlobalValidator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current Node.
		this.header = header;
		
		return this;
	}
	
	//TOOD: Delete this method.
	public void reset() {
		removeHeader();
		removeChildNodes();
	}
}
