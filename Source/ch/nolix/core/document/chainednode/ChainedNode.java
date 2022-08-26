//package declaration
package ch.nolix.core.document.chainednode;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
/**
 * A {@link ChainedNode} has the following attributes.
 * -0 or 1 header
 * -an arbitrary number of child {@link ChainedNode}s
 * -0 or 1 next {@link ChainedNode}
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class ChainedNode implements IChainedNode {
	
	//constant
	public static final String DOT_CODE = "$D";
	
	//constant
	public static final String COMMA_CODE = "$M";
	
	//constant
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	
	//constant
	public static final String OPEN_BRACKET_CODE = "$O";
	
	//constant
	public static final String CLOSED_BRACKET_CODE = "$C";
	
	//constant
	private static final String NEXT_NODE_VARIABLE_NAME = "next node";
	
	//static method
	/**
	 * @param node
	 * @return a new {@link ChainedNode} from the given node.
	 */
	public static ChainedNode fromNode(final INode<?> node) {
		
		final var chainedNode = new ChainedNode();
		
		if (node.hasHeader()) {
			chainedNode.setHeader(node.getHeader());
		}
		
		chainedNode.addChildNodesFromNodes(node.getRefChildNodes());
				
		return chainedNode;
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link ChainedNode} the given string represents.
	 * @throws UnrepresentingArgumentException if the given string does not represent a {@link ChainedNode}.
	 */
	public static ChainedNode fromString(final String string) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.resetFrom(string);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param string
	 * @return an escape {@link String} for the given string.
	 */
	public static String getEscapeStringFor(final String string) {
		return
		string
		
		//It is essential to replace the dollar symbol at first.
		.replace(String.valueOf(CharacterCatalogue.DOLLAR), DOLLAR_SYMBOL_CODE)
		
		.replace(String.valueOf(CharacterCatalogue.DOT), DOT_CODE)
		.replace(String.valueOf(CharacterCatalogue.COMMA), COMMA_CODE)
		.replace(String.valueOf(CharacterCatalogue.OPEN_BRACKET), OPEN_BRACKET_CODE)
		.replace(String.valueOf(CharacterCatalogue.CLOSED_BRACKET), CLOSED_BRACKET_CODE);
	}
	
	//static method
	/**
	 * @param escapeString
	 * @return an origin {@link String} from the given escapeString.
	 */
	public static String getOriginStringFromEscapeString(final String escapeString) {
			
		return
		escapeString
		.replace(DOT_CODE, String.valueOf(CharacterCatalogue.DOT))
		.replace(COMMA_CODE, String.valueOf(CharacterCatalogue.COMMA))
		.replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalogue.OPEN_BRACKET))
		.replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalogue.CLOSED_BRACKET))
		
		//It is essential to replace the dollar symbol code at last.
		.replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalogue.DOLLAR));
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link ChainedNode} with the given attributes.
	 * @throws ArgumentIsNullException if one of the given attributes is null.
	 */
	public static ChainedNode withChildNodesFromNodes(final INode<?>... attributes) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.addChildNode(attributes);
		
		return chainedNode;
	}
	
	//static method
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
	
	//static method
	/**
	 * @param header
	 * @return a new {@link ChainedNode} with the given header.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static ChainedNode withHeader(final String header) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link ChainedNode} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given attribute is null.
	 */
	public static ChainedNode withHeaderAndChildNode(final String header, final ChainedNode attribute) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.addChildNode(attribute);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param header
	 * @param childNode
	 * @return a new {@link ChainedNode} with the given header and childNode.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static ChainedNode withHeaderAndChildNode(final String header, final INode<?> childNode) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.addChildNode(childNode);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param header
	 * @param attributes
	 * @return a new {@link ChainedNode} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attribute is null.
	 */
	public static ChainedNode withHeaderAndChildNodes(
		final String header,
		final Iterable<? extends IChainedNode> attributes
	) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.addChildNodes(attributes);
		
		return chainedNode;
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @return a new {@link ChainedNode} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attributes is null.
	 */
	public static ChainedNode withHeaderAndChildNodesFromNodes(final String header, final INode<?>... attributes) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.addChildNodesFromNodes(attributes);
		
		return chainedNode;
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @return a new {@link ChainedNode} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attributes is null.
	 */
	public static ChainedNode withHeaderAndChildNodesFromNodes(
		final String header,
		final Iterable<INode<?>> attributes
	) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.addChildNodesFromNodes(attributes);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param header
	 * @param nextNode
	 * @return a new {@link ChainedNode} with the given header and nextNode.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given nextNode is null.
	 */
	public static ChainedNode withHeaderAndNextNode(final String header, ChainedNode nextNode) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.setNextNode(nextNode);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param header
	 * @param nextNode
	 * @param childNodes
	 * @return a new {@link ChainedNode} with the given header and nextNode.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given nextNode is null.
	 * @throws ArgumentIsNullException if one of the given childNodes is null.
	 */
	public static ChainedNode withHeaderAndNextNodeAndChildNodes(
		final String header,
		ChainedNode nextNode,
		final IChainedNode... childNodes
	) {
		
		final var chainedNode = new ChainedNode();
		
		chainedNode.setHeader(header);
		
		//TODO: Overload addChildNode method.
		chainedNode.addChildNodes(ReadContainer.forArray(childNodes));
		
		chainedNode.setNextNode(nextNode);
		
		return chainedNode;
	}
	
	//static method
	/**
	 * @param chainedNode
	 * @return a {@link ChainedNode} from the given chainedNode.
	 */
	private static ChainedNode fromChainedNode(final IChainedNode chainedNode) {
		
		if (chainedNode instanceof ChainedNode) {
			return (ChainedNode)chainedNode;
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
	
	//optional attributes
	private String header;
	private ChainedNode nextNode;
	
	//multi-attribute
	private final LinkedList<ChainedNode> childNodes = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode}.
	 */
	public ChainedNode() {
		header = null;
		nextNode = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public ChainedNode(final String header, final Iterable<INode<?>> attributes) {
		setHeader(header);	
		addChildNodesFromNodes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header, attributes and nextNode.
	 * 
	 * @param header
	 * @param attributes
	 * @param nextNode
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given nextNode is null.
	 */
	public ChainedNode(
		final String header,
		final Iterable<INode<?>> attributes,
		final ChainedNode nextNode
	) {
		setHeader(header);	
		addChildNodesFromNodes(attributes);
		setNextNode(nextNode);
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} contains attributes.
	 */
	public boolean containsAttributes() {
		return childNodes.containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		return (object instanceof ChainedNode && equals((ChainedNode)object));
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link ChainedNode}.
	 */
	public int getChildNodeCount() {
		return childNodes.getElementCount();
	}
	
	//method
	/**
	 * @param index
	 * @return the attribute at the given index of the current {@link ChainedNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ChainedNode} does not contain an attribute at the given index.
	 */
	public ChainedNode getChildNodeAt1BasedIndex(final int index) {
		return childNodes.getRefAt1BasedIndex(index);
	}

	//method
	/**
	 * @return the attributes of the current {@link ChainedNode}.
	 */
	public IContainer<ChainedNode> getChildNodes() {
		return childNodes;
	}
	
	//method
	/**
	 * @return the {@link Node} representations of the attributes of the current {@link ChainedNode}.
	 * @throws UnrepresentingArgumentException
	 * if one of the attributes of the current {@link ChainedNode} does not represent a {@link Node}.
	 */
	public IContainer<Node> getChildNodesAsNodes() {
		return childNodes.to(ChainedNode::toNode);
	}

	//method
	/**
	 * @return a {@link String} representation of the attributes of the current {@link ChainedNode}.
	 */
	public String getChildNodesAsString() {
		return childNodes.toString();
	}
	
	//method
	/**
	 * @return the {@link String} representations of the attributes of the current {@link ChainedNode}.
	 */
	public IContainer<String> getChildNodesAsStrings() {
		return childNodes.toStrings();
	}
	
	//method
	/**
	 * @return a reproducing {@link String} representation of the header of the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	public String getEscapeHeader() {
		
		//Asserts that the current ChainedNode has a header.
		if (header == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.HEADER);
		}
		
		return getEscapeStringFor(header);
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute with the given header from the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link ChainedNode} does not contain an attribute with the given header.
	 */
	public ChainedNode getFirstChildNodeWithHeader(final String header) {
		return getChildNodes().getRefFirst(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * @return the header of the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Asserts that the current ChainedNode has a header.
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
	public String getHeaderOrEmptyString() {
		
		//Handles the case that current ChainedNode does not have a header.
		if (!hasHeader()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		//Handles the case that current ChainedNode has a header.
		return getHeader();
	}
	
	//method
	/**
	 * @return the next node of the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a next node.
	 */
	public ChainedNode getNextNode() {
		
		//Asserts that the current ChanedNode has a next node.
		if (nextNode == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, NEXT_NODE_VARIABLE_NAME);
		}
		
		return nextNode;
	}

	//method
	/**
	 * @return a {@link String} representation of the next node of the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a next node.
	 */
	public String getNextNodeAsString() {
		
		//Asserts that the current ChainedNode has a next node.
		if (nextNode == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, NEXT_NODE_VARIABLE_NAME);
		}
		
		return nextNode.toString();
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public ChainedNode getSingleChildNode() {
		return childNodes.getRefOne();
	}
	
	//method
	/**
	 * @return the header of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the one attribute of the current {@link ChainedNode} does not have a header.
	 */
	public String getSingleChildNodeHeader() {
		return getSingleChildNode().getHeader();
	}
	
	//method
	/**
	 * @return a {@link Double} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws UnrepresentingArgumentException if
	 * the one attribute of the current {@link ChainedNode} does not represent a {@link Double}.
	 */
	public double getSingleChildNodeAsDouble() {
		return getSingleChildNode().toDouble();
	}
	
	//method
	/**
	 * @return a {@link Integer} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws UnrepresentingArgumentException
	 * if the one attribute of the current {@link ChainedNode} does not represent a {@link Integer}.
	 */
	public int getSingleChildNodeAsInt() {
		return getSingleChildNode().toInt();
	}
	
	//method
	/**
	 * @return a {@link Node} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws UnrepresentingArgumentException
	 * if the one attribute of the current {@link ChainedNode} does not represent a {@link Node}.
	 */
	public Node getSingleChildNodeAsNode() {
		return getSingleChildNode().toNode();
	}
	
	//method
	/**
	 * @return a {@link String} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public String getSingleChildNodeAsString() {
		return getSingleChildNode().toString();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasHeader(final String header) {
		
		//Handles the case that current ChainedNode does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that current ChainedNode has a header.
		return getHeader().equals(header);
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} does not have a header and does not contains attributes.
	 */
	public boolean isBlank() {
		return
		!hasHeader()
		&& !containsAttributes();
	}
	
	//method
	/**
	 * @return a {@link Double} representation of the current {@link ChainedNode}.
	 * @throws UnrepresentingArgumentException if the current {@link ChainedNode} does not represent a {@link Double}.
	 */
	public double toDouble() {
		
		//Asserts that the current ChainedNode can represent a Double.
		if (header == null || childNodes.containsAny()) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
		}
		
		return Double.valueOf(header);
	}
	
	//method
	/**
	 * @return a {@link Integer} representation of the current {@link ChainedNode}.
	 * @throws UnrepresentingArgumentException if the current {@link ChainedNode} does not represent a {@link Integer}.
	 */
	public int toInt() {
		
		//Asserts that the current ChainedNode can represent an Integer.
		if (header == null || childNodes.containsAny()) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
		}
		
		return GlobalStringHelper.toInt(header);
	}
	
	//method
	/**
	 * A {@link ChainedNode} represents a {@link Node} if:
	 * -The {@link ChainedNode} does not have a next node.
	 * -Each attribute of the {@link ChainedNode} represents a {@link Node}.
	 * 
	 * @return a {@link Node} representation of the current {@link ChainedNode}.
	 * @throws UnrepresentingArgumentException if the current {@link ChainedNode} does not represent a {@link Node}.
	 */
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final var stringBuilder = new StringBuilder();
		appendStringRepresentationTo(stringBuilder);
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link ChainedNode}.
	 * 
	 * @param attribute
	 * @throws ArgumentIsNullException if the given attribute is null.
	 */
	private void addChildNode(final IChainedNode attribute) {
		if (attribute instanceof ChainedNode) {
			childNodes.addAtEnd((ChainedNode)attribute);
		} else {
			childNodes.addAtEnd(fromChainedNode(attribute));
		}
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	private void addChildNode(final INode<?>... attributes) {
		for (final var a : attributes) {
			this.childNodes.addAtEnd(fromNode(a));
		}
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 * @throws ArgumentIsNullException if one of the given attribute is null.
	 */
	private void addChildNodes(final Iterable<? extends IChainedNode> attributes) {
		attributes.forEach(this::addChildNode);
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	private void addChildNodesFromNodes(final INode<?>[] attributes) {
		for (final var a : attributes) {
			this.childNodes.addAtEnd(fromNode(a));
		}
	}
	
	//method
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
	
	//method
	/**
	 * Appends the {@link String} representation of the current {@link ChainedNode} to the given stringBuilder.
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
			
			boolean atBegin = true;
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
	
	//method
	/**
	 * @param chainedNode
	 * @return true if the current {@link ChainedNode} equals the given chainedNode.
	 */
	private boolean equals(final ChainedNode chainedNode) {
		return
		canEqualBecauseOfHeader(chainedNode)
		&& canEqualBecauseOfChildNodes(chainedNode);
	}
	
	//method
	/**
	 * @param chainedNode
	 * @return true if the current {@link ChainedNode} can equal the given chainedNode because of the attributes.
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
	
	//method
	/**
	 * @param chainedNode
	 * @return true if the current {@link ChainedNode} can equal the given chainedNode because of the header.
	 */
	private boolean canEqualBecauseOfHeader(final ChainedNode chainedNode) {
		
		if (!hasHeader()) {
			return !chainedNode.hasHeader();
		}
		
		return (chainedNode.hasHeader() && hasHeader(chainedNode.getHeader()));
	}
	
	//method
	/**
	 * @param string
	 * @param startIndex
	 * @return the length of the probable header of the {@link ChainedNode}
	 * the given string represents starting from the given startIndex.
	 */
	private HeaderLengthAndTaskAfterHeaderParameter getHeaderLengthAndTaskAfterHeader(
		final String string,
		final int startIndex
	) {
		
		var nextIndex = startIndex;
		while (nextIndex < string.length()) {
			switch (string.charAt(nextIndex)) {
				case CharacterCatalogue.OPEN_BRACKET:
					return
					new HeaderLengthAndTaskAfterHeaderParameter(
						nextIndex - startIndex,
						TaskAfterHeader.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE
					);
				case CharacterCatalogue.COMMA:
					return new HeaderLengthAndTaskAfterHeaderParameter(nextIndex - startIndex, TaskAfterHeader.DO_NOTHING);
				case CharacterCatalogue.CLOSED_BRACKET:
					return new HeaderLengthAndTaskAfterHeaderParameter(nextIndex - startIndex, TaskAfterHeader.DO_NOTHING);
				case CharacterCatalogue.DOT:
					return new HeaderLengthAndTaskAfterHeaderParameter(nextIndex - startIndex, TaskAfterHeader.READ_NEXT_NODE);
				default:
					nextIndex++;
			}
		}
		
		return new HeaderLengthAndTaskAfterHeaderParameter(nextIndex - startIndex, TaskAfterHeader.DO_NOTHING);
	}
	
	//method
	/**
	 * Resets the current {@link ChainedNode}.
	 */
	private void reset() {
		header = null;
		childNodes.clear();
		nextNode = null;
	}
	
	//method
	/**
	 * Resets the current {@link ChainedNode} from the given string.
	 * 
	 * @param string
	 * @throws UnrepresentingArgumentException if the given string does nor represent a {@link ChainedNode}.
	 */
	private void resetFrom(final String string) {
		
		reset();
		
		if (setAndGetNextIndex(string, 0) != string.length()) {
			throw UnrepresentingArgumentException.forArgumentAndType(string, ChainedNode.class);
		}
	}

	//method
	/**
	 * Sets the current {@link ChainedNode} from the given string starting from the given startIndex.
	 * The given startIndex and the returned next index are zero-based.
	 * 
	 * @param string
	 * @param startIndex
	 * @return the next index the given string can be processed from.
	 */
	private int setAndGetNextIndex(final String string, final int startIndex) {
		
		final var headerLengthAndTaskAfterHeader = getHeaderLengthAndTaskAfterHeader(string, startIndex);
		
		setProbableHeader(string, startIndex, headerLengthAndTaskAfterHeader.getHeaderLength());
		
		var nextIndex = startIndex + headerLengthAndTaskAfterHeader.getHeaderLength();
		final var taskAfterHeader = headerLengthAndTaskAfterHeader.getTaskAfterHeader();
		if (
			taskAfterHeader == TaskAfterHeader.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE
			|| taskAfterHeader == TaskAfterHeader.READ_NEXT_NODE
		) {
			nextIndex++;
		}
		
		var readNextNode = false;
		switch (taskAfterHeader) {
			case READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
				
				final var node = new ChainedNode();
				nextIndex = node.setAndGetNextIndex(string, nextIndex);
				this.childNodes.addAtEnd(node);
				
				while (nextIndex < string.length()) {
					
					final var character = string.charAt(nextIndex);
					
					if (character == ',') {
						final var node2 = new ChainedNode();
						nextIndex = node2.setAndGetNextIndex(string, nextIndex + 1);
						this.childNodes.addAtEnd(node2);
					} else if (character == ')') {
						nextIndex++;
						break;
					}
				}
				
				if (nextIndex < string.length() - 1 && string.charAt(nextIndex) == '.') {
					nextIndex++;
					readNextNode = true;
				}
				
				break;
			case DO_NOTHING:
				return nextIndex;
			case READ_NEXT_NODE:
				readNextNode = true;
		}
		
		if (!readNextNode) {
			return nextIndex;
		}
		
		nextNode = new ChainedNode();
		return nextNode.setAndGetNextIndex(string, nextIndex);
	}
	
	//method
	/**
	 * Sets the header of the current {@link ChainedNode}.
	 * 
	 * @param header
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private void setHeader(final String header) {
		
		//Asserts that the given header is not null.
		if (header == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.HEADER);
		}
		
		//Asserts that the given header is not blank.
		if (header.isBlank()) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				LowerCaseCatalogue.HEADER,
				header,
				"is blank"
			);
		}
		
		this.header = header;
	}
	
	//method
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
		
		if (nextNode instanceof ChainedNode) {
			this.nextNode = (ChainedNode)nextNode;
		} else {
			this.nextNode = fromChainedNode(nextNode);
		}
	}
	
	//method
	/**
	 * Sets the probable header of the current {@link ChainedNode}.
	 * The header is in the given string starting from the given startIndex and has the given headerLength.
	 * 
	 * @param string
	 * @param startIndex
	 * @param headerLength
	 */
	private void setProbableHeader(final String string, final int startIndex, final int headerLength) {
		if (headerLength > 0) {
			this.header =
			getOriginStringFromEscapeString(
				string.substring(startIndex, startIndex + headerLength)
			);
		}
	}
}
