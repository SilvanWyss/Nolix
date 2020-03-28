//package declaration
package ch.nolix.common.chainedNode;

//own imports
import ch.nolix.common.attributeAPI.OptionalHeadered;
import ch.nolix.common.commonTypeHelpers.StringHelper;
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.UnrepresentingArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ChainedNode} can have:
 * -1 header
 * -several attributes that are {@link ChainedNode}s
 * -a next node that is a {@link ChainedNode}
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 660
 */
public final class ChainedNode implements OptionalHeadered {
	
	//constants
	public static final String DOT_CODE = "$D";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	
	//static method
	/**
	 * @param string
	 * @return a reproducing {@link String} for the given string.
	 */
	public static String createReproducingString(final String string) {
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
	 * @param string
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
	public static ChainedNode withHeaderAndAttributes(final String header, final Iterable<ChainedNode> attributes) {
		
		final var chainedNode = new ChainedNode();
		chainedNode.setHeader(header);
		chainedNode.attributes.addAtEnd(attributes);
		
		return chainedNode;
	}
	
	//optional attribute
	private String header;
	
	//optional attribute
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
	public ChainedNode(final IContainer<BaseNode> attributes) {
		addAttributes(attributes);
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
	public ChainedNode(final String header, final IContainer<BaseNode> attributes) {
		setHeader(header);	
		addAttributes(attributes);
	}
	
	//constructor
	public <N extends BaseNode> ChainedNode(
		final String header,
		final IContainer<N> attributes,
		final ChainedNode nextNode
	) {
		setHeader(header);	
		addAttributes(attributes);
		setNextNode(nextNode);
	}
	
	//constructor
	public <N extends BaseNode> ChainedNode(final String header, final Iterable<N> attributes) {
		setHeader(header);	
		addAttributes(attributes);
	}
	
	//constructor
	public <N extends BaseNode> ChainedNode(
		final String header,
		final Iterable<N> attributes,
		final ChainedNode nextNode
	) {
		setHeader(header);	
		addAttributes(attributes);
		setNextNode(nextNode);
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public <N extends BaseNode> ChainedNode(final String header, final N... attributes) {
		setHeader(header);
		addAttributes(attributes);
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
		return attributes.getSize();
	}
	
	//method
	/**
	 * @param index
	 * @return the attribute at the given index of the current {@link ChainedNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode}
	 * does not contain an attribute at the given index.
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
	public LinkedList<Node> getAttributesAsNodes() {
		return attributes.to(a -> a.toNode());
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
	public LinkedList<String> getAttributesAsStrings() {
		return attributes.toStrings();
	}
	
	//method
	/**
	 * @return the header of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Checks if the current ChainedNode has a header.
		if (header == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return header;
	}
	
	//method
	/**
	 * @return the next node of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a next node.
	 */
	public ChainedNode getNextNode() {
		
		//Checks if the current ChanedNode has a next node.
		if (nextNode == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next node");
		}
		
		return nextNode;
	}

	//method
	/**
	 * @return a {@link String} representation of the next node of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a next node.
	 */
	public String getNextNodeAsString() {
		
		//Checks if the current ChainedNode has a next node.
		if (nextNode == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next node");
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
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	public String getReproducingHeader() {
		
		//Checks if the current ChainedNode has a header.
		if (header == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return createReproducingString(header);
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
		
		//Checks if the current ChainedNode can represent an Integer.
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
		
		//Checks if the current ChainedNode can represent a Node.
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
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	private <N extends BaseNode> void addAttributes(final Iterable<N> attributes) {
		for (final var a : attributes) {
			this.attributes.addAtEnd(fromNode(a));
		}
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link ChainedNode}.
	 * 
	 * @param attributes
	 */
	@SuppressWarnings("unchecked")
	private <N extends BaseNode> void addAttributes(final N... attributes) {
		for (final var a : attributes) {
			this.attributes.addAtEnd(fromNode(a));
		}
	}
	
	//method
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
	//startIndex = index of first read char in the substring array (zero-based)
	//nextIndex = index of the next non-read char in the substring array (zero-based)
	private int setAndGetNextIndex(final String substring, final int startIndex) {
		
		var nextIndex = startIndex;
		
		var taskAfterSetProbableHeader = Task.DO_NOTHING;
		var headerLength = 0;
		while (nextIndex < substring.length()) {
			
			var character = substring.charAt(nextIndex);
			
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
				taskAfterSetProbableHeader =Task.READ_NEXT_NODE;
				nextIndex++;
				break;
			}
			
			nextIndex++;
			headerLength++;
		}
		
		//Sets probable header.
		if (headerLength > 0) {
			this.header = BaseNode.createOriginStringFromReproducingString(substring.substring(startIndex, startIndex + headerLength));
		}
		
		var readNextNode = false;
		switch (taskAfterSetProbableHeader) {
			case READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
				
				final var node = new ChainedNode();
				nextIndex = node.setAndGetNextIndex(substring, nextIndex);
				this.attributes.addAtEnd(node);
				
				while (nextIndex < substring.length()) {
					
					final var character = substring.charAt(nextIndex);
					
					if (character == ',') {
						final var node2 = new ChainedNode();
						nextIndex = node2.setAndGetNextIndex(substring, nextIndex + 1);
						this.attributes.addAtEnd(node2);
					}
					
					else if (character == ')') {
						nextIndex++;
						break;
					}
				}
				
				if (nextIndex < substring.length() - 1 && substring.charAt(nextIndex) == '.') {
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
		return nextNode.setAndGetNextIndex(substring, nextIndex);
	}
	
	//method
	/**
	 * Sets the header of the current {@link ChainedNode}.
	 * 
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	private void setHeader(final String header) {
		
		Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
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
		
		Validator.suppose(nextNode).thatIsNamed("next node").isNotNull();
		
		this.nextNode = nextNode;
	}
}
