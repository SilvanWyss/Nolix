//package declaration
package ch.nolix.common.chainedNode;

//own imports
import ch.nolix.common.commonTypeHelper.StringHelper;
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.invalidArgumentException.UnrepresentingArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.optionalAttributeAPI.OptionalHeadered;

//class
/**
 * A {@link ChainedNode} can have:
 * -1 header
 * -several attributes that are {@link ChainedNode}s
 * -a next node which is a {@link ChainedNode}
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 770
 */
public final class ChainedNode implements OptionalHeadered {
	
	//constants
	public static final String DOT_CODE = "$D";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	
	//constant
	private static final String NEXT_NODE_VARIABLE_NAME = "next node";
	
	//static method
	/**
	 * @param string
	 * @return a reproducing {@link String} for the given string.
	 */
	public static String createReproducingStringFor(final String string) {
		return
		string
		.replace(String.valueOf(CharacterCatalogue.DOLLAR), DOLLAR_SYMBOL_CODE)
		.replace(String.valueOf(CharacterCatalogue.DOT), DOT_CODE)
		.replace(String.valueOf(CharacterCatalogue.COMMA), BaseNode.COMMA_CODE)
		.replace(String.valueOf(CharacterCatalogue.OPEN_BRACKET), OPEN_BRACKET_CODE)
		.replace(String.valueOf(CharacterCatalogue.CLOSED_BRACKET), CLOSED_BRACKET_CODE);
	}
	
	//static method
	/**
	 * @param node
	 * @return a new {@link ChainedNode} from the given node.
	 */
	public static ChainedNode fromNode(final BaseNode node) {
		
		if (!node.hasHeader()) {
			return new ChainedNode(node.getRefAttributes());
		}
		
		return new ChainedNode(node.getHeader(), node.getRefAttributes());
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link ChainedNode} the given string represents.
	 * @throws NonRepresentingArgumentException if the given string does not represent a {@link ChainedNode}.
	 */
	public static ChainedNode fromString(final String string) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.reset(string);
		
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
	 * @param attributes
	 * @return a new {@link ChainedNode} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attribute is null.
	 */
	public static ChainedNode withHeaderAndAttributes(final String header, final IContainer<ChainedNode> attributes) {
		
		final var chainedNode = new ChainedNode(header);
		chainedNode.addAttributes(attributes);
		
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
		
		final var chainedNode = new ChainedNode(header);
		chainedNode.setNextNode(nextNode);
		
		return chainedNode;
	}
	
	//optional attributes
	private String header;
	private ChainedNode nextNode;
	
	//multi-attribute
	private final LinkedList<ChainedNode> attributes = new LinkedList<>();
	
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
	 * Creates a new {@link ChainedNode} with the given attributes.
	 * 
	 * @param attributes
	 */
	public <BN extends BaseNode> ChainedNode(final Iterable<BN> attributes) {
		addAttributesFromNodes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header.
	 * 
	 * @param header
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public ChainedNode(final String header) {
		setHeader(header);
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
	@SuppressWarnings("unchecked")
	public <BN extends BaseNode> ChainedNode(final String header, final BN... attributes) {
		setHeader(header);
		addAttributes(attributes);
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
	public <BN extends BaseNode> ChainedNode(final String header, final Iterable<BN> attributes) {
		setHeader(header);	
		addAttributesFromNodes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} with the given header, attributes and nextNode.
	 * 
	 * @param header
	 * @param attributes
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given nextNode is null.
	 */
	public <BN extends BaseNode> ChainedNode(
		final String header,
		final Iterable<BN> attributes,
		final ChainedNode nextNode
	) {
		setHeader(header);	
		addAttributesFromNodes(attributes);
		setNextNode(nextNode);
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} contains attributes.
	 */
	public boolean containsAttributes() {
		return attributes.containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof ChainedNode)) {
			return false;
		}
		
		final var chainedNode = (ChainedNode)object;
		
		if (!hasSameHeaderAs(chainedNode)) {
			return false;
		}
		
		if (getAttributeCount() != chainedNode.getAttributeCount()) {
			return false;
		}
		
		var i = 1;
		for (final var a : getAttributes()) {
			if (!a.equals(chainedNode.getAttributeAt(i++))) {
				return false;
			}
		}
		
		if (!hasNextNode()) {
			if (chainedNode.hasNextNode()) {
				return false;
			}
		}
		else {
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
	 * @return the number of attributes of the current {@link ChainedNode}.
	 */
	public int getAttributeCount() {
		return attributes.getElementCount();
	}
	
	//method
	/**
	 * @param index
	 * @return the attribute at the given index of the current {@link ChainedNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ChainedNode} does not contain an attribute at the given index.
	 */
	public ChainedNode getAttributeAt(final int index) {
		return attributes.getRefAt(index);
	}

	//method
	/**
	 * @return the attributes of the current {@link ChainedNode}.
	 */
	public IContainer<ChainedNode> getAttributes() {
		return attributes;
	}
	
	//method
	/**
	 * @return the {@link Node} representations of the attributes of the current {@link ChainedNode}.
	 * @throws NonRepresentingArgumentException
	 * if one of the attributes of the current {@link ChainedNode} does not represent a {@link Node}.
	 */
	public IContainer<Node> getAttributesAsNodes() {
		return attributes.to(ChainedNode::toNode);
	}

	//method
	/**
	 * @return a {@link String} representation of the attributes of the current {@link ChainedNode}.
	 */
	public String getAttributesAsString() {
		return attributes.toString();
	}
	
	//method
	/**
	 * @return the {@link String} representations of the attributes of the current {@link ChainedNode}.
	 */
	public IContainer<String> getAttributesAsStrings() {
		return attributes.toStrings();
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
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return header;
	}
	
	//method
	/**
	 * @return the next node of the current {@link ChainedNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a next node.
	 */
	public ChainedNode getNextNode() {
		
		//Asserts that the current ChanedNode has a next node.
		if (nextNode == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, NEXT_NODE_VARIABLE_NAME);
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
			throw new ArgumentDoesNotHaveAttributeException(this, NEXT_NODE_VARIABLE_NAME);
		}
		
		return nextNode.toString();
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public ChainedNode getOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @return a {@link Integer} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws NonRepresentingArgumentException
	 * if the one attribute of the current {@link ChainedNode} does not represent a {@link Integer}.
	 */
	public int getOneAttributeAsInt() {
		return getOneAttribute().toInt();
	}
	
	//method
	/**
	 * @return a {@link Node} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws NonRepresentingArgumentException
	 * if the one attribute of the current {@link ChainedNode} does not represent a {@link Node}.
	 */
	public Node getOneAttributeAsNode() {
		return getOneAttribute().toNode();
	}
	
	//method
	/**
	 * @return a {@link String} representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public String getOneAttributeAsString() {
		return getOneAttribute().toString();
	}
	
	//method
	/**
	 * @return a reproducing {@link String} representation of the header of the current {@link BaseNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	public String getReproducingHeader() {
		
		//Asserts that the current ChainedNode has a header.
		if (header == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return createReproducingStringFor(header);
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
	 * @return true if the current {@link ChainedNode} has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * @return a {@link Integer} representation of the current {@link ChainedNode}.
	 * @throws NonRepresentingArgumentException
	 * if the current {@link ChainedNode} does not represent a {@link Integer}.
	 */
	public int toInt() {
		
		//Asserts that the current ChainedNode can represent an Integer.
		if (header == null || attributes.containsAny()) {
			throw new UnrepresentingArgumentException(this, Integer.class);
		}
		
		return StringHelper.toInt(header);
	}
	
	//method
	/**
	 * A {@link ChainedNode} represents a {@link Node} if:
	 * -The {@link ChainedNode} does not have a next node.
	 * -Each attribute of the {@link ChainedNode} represents a {@link Node}.
	 * 
	 * @return a {@link Node} representation of the current {@link ChainedNode}.
	 * @throws NonRepresentingArgumentException if the current {@link ChainedNode} does not represent a {@link Node}.
	 */
	public Node toNode() {
		
		//Asserts that the current ChainedNode can represent a Node.
		if (nextNode != null) {
			throw new UnrepresentingArgumentException(this, Node.class);
		}
		
		//Creates a Node.
		final var node = new Node();
		
		//Handles the case that the current ChainedNode has a header.
		if (header != null) {
			node.setHeader(header);
		}
		
		//Iterates the attributes of the current ChainedNode.
		for (final var a : attributes) {
			node.addAttribute(a.toNode());
		}
		
		return node;
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
	 * Adds the given attribtue to the current {@link ChainedNode}.
	 * 
	 * @param attribute
	 * @throws ArgumentIsNullException if the given attribute is null.
	 */
	private void addAttribute(final ChainedNode attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	@SuppressWarnings("unchecked")
	private <BN extends BaseNode> void addAttributes(final BN... attributes) {
		for (final var a : attributes) {
			this.attributes.addAtEnd(fromNode(a));
		}
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 * @throws ArgumentIsNullException if one of the given attribute is null.
	 */
	private void addAttributes(final Iterable<ChainedNode> attributes) {
		attributes.forEach(this::addAttribute);
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	private <BN extends BaseNode> void addAttributesFromNodes(final Iterable<BN> attributes) {
		for (final var a : attributes) {
			this.attributes.addAtEnd(fromNode(a));
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
			stringBuilder.append(BaseNode.createReproducingString(header));
		}
		
		//Handles the case that the current ChainedNode contains attributes.
		if (attributes.containsAny()) {
			
			stringBuilder.append("(");
			
			boolean atBegin = true;
			for (final var a : attributes) {
				
				if (atBegin) {
					atBegin = false;
				}
				else {
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
	 * Resets the current {@link ChainedNode}.
	 */
	private void reset() {
		header = null;
		attributes.clear();
		nextNode = null;
	}
	
	//method
	/**
	 * Resets the current {@link ChainedNode} from the given string.
	 * 
	 * @param string
	 * @throws UnrepresentingArgumentException if the given string does nor represent a {@link ChainedNode}.
	 */
	private void reset(final String string) {
		
		reset();
		
		if (setAndGetNextIndex(string, 0) != string.length()) {
			throw new UnrepresentingArgumentException(string, ChainedNode.class);
		}
	}

	//method
	/**
	 * Sets the current {@link ChainedNode} from the given string from the given startIndex.
	 * The given startIndex and the returned next index are zero-based.
	 * 
	 * @param string
	 * @param startIndex
	 * @return the next index the given string can be processed from.
	 */
	private int setAndGetNextIndex(final String string, final int startIndex) {
		
		var nextIndex = startIndex;
		
		var taskAfterSetProbableHeader = Task.DO_NOTHING;
		var headerLength = 0;
		while (nextIndex < string.length()) {
			
			var character = string.charAt(nextIndex);
			
			if (character == '(') {
				taskAfterSetProbableHeader = Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE;
				nextIndex++;
				break;
			}
			
			if (character == ',') {
				break;
			}
			
			if (character == ')') {
				break;
			}
			
			if (character == '.') {
				taskAfterSetProbableHeader = Task.READ_NEXT_NODE;
				nextIndex++;
				break;
			}
			
			nextIndex++;
			headerLength++;
		}
		
		//Sets probable header.
		setProbableHeader(string, startIndex, headerLength);
		
		var readNextNode = false;
		switch (taskAfterSetProbableHeader) {
			case READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
				
				final var node = new ChainedNode();
				nextIndex = node.setAndGetNextIndex(string, nextIndex);
				this.attributes.addAtEnd(node);
				
				while (nextIndex < string.length()) {
					
					final var character = string.charAt(nextIndex);
					
					if (character == ',') {
						final var node2 = new ChainedNode();
						nextIndex = node2.setAndGetNextIndex(string, nextIndex + 1);
						this.attributes.addAtEnd(node2);
					}
					
					else if (character == ')') {
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
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private void setHeader(final String header) {
		
		//Asserts that the given header is not null.
		if (header == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.HEADER);
		}
		
		//Asserts that the given header is not blank.
		if (header.isBlank()) {
			throw new InvalidArgumentException(VariableNameCatalogue.HEADER, header, "is blank");
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
	private void setNextNode(final ChainedNode nextNode) {
		
		//Asserts that the given nextNode is not null.
		if (nextNode == null) {
			throw new ArgumentIsNullException(NEXT_NODE_VARIABLE_NAME);
		}
		
		this.nextNode = nextNode;
	}
	
	//method
	/**
	 * Sets the probable header of the current {@link ChainedNode}.
	 * The header is in the given string from the given startIndex and has the given headerLength.
	 * 
	 * @param string
	 * @param startIndex
	 * @param headerLength
	 */
	private void setProbableHeader(final String string, final int startIndex, final int headerLength) {
		if (headerLength > 0) {
			this.header =
			BaseNode.createOriginStringFromReproducingString(
				string.substring(startIndex, startIndex + headerLength)
			);
		}
	}
}
