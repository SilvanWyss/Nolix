//package declaration
package ch.nolix.common.node;

//own imports
import ch.nolix.common.commontypehelper.BooleanHelper;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.functionapi.IElementTakerBooleanGetter;
import ch.nolix.common.generalskillapi.ISmartObject;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.pair.IntPair;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Node} is a {@link BaseNode}
 * that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 720
 */
public final class Node extends BaseNode implements ISmartObject<Node> {
	
	//static method
	/**
	 * @param filePath
	 * @return a new {@link Node} from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException if the file with the given file path does not represent a {@link Node}.
	 */
	public static Node fromFile(final String filePath) {
		
		final var node = new Node();
		node.resetFromFile(filePath);
		
		return node;
	}
	
	//static method
	/**
	 * @param intPair
	 * @return a new {@link Node} from the given intPair.
	 * @throws ArgumentIsNullException if the given intPair is null.
	 */
	public static Node fromIntPair(final IntPair intPair) {
		
		Validator.assertThat(intPair).thatIsNamed(IntPair.class).isNotNull();
		
		return withAttribute(intPair.getValue1(), intPair.getValue2());
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link Node} from the given string.
	 * @throws InvalidArgumentException if the given string does not represent a {@link Node}.
	 */
	public static Node fromString(final String string) {
		
		final var node = new Node();
		node.reset(string);
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 * @throws ArgumentIsNullException if the given attribute is null.
	 */
	public static <BN extends BaseNode> Node withAttribute(final BN attribute) {
		
		final var node = new Node();
		node.addAttribute(attribute);
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	@SuppressWarnings("unchecked")
	public static <BN extends BaseNode> Node withAttribute(final BN... attributes) {
		
		final var node = new Node();
		node.addAttribute(attributes);
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static Node withAttribute(final boolean attribute) {
		
		final var node = new Node();
		node.addAttribute(Node.withHeader(String.valueOf(attribute)));
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	public static Node withAttribute(final boolean... attributes) {
		
		final var node = new Node();
		for (var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static Node withAttribute(final double attribute) {
		
		final var node = new Node();
		node.addAttribute(String.valueOf(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	public static Node withAttribute(final double... attributes) {
		
		final var node = new Node();
		for (var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static Node withAttribute(final long attribute) {
		
		final var node = new Node();
		node.addAttribute(String.valueOf(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	public static Node withAttribute(final long... attributes) {
		
		final var node = new Node();
		for (var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 * @throws ArgumentIsNullException if the given attribute is null.
	 * @throws InvalidArgumentException with the given attribute.
	 */
	public static Node withAttribute(final String attribute) {
		
		final var node = new Node();
		node.addAttribute(Node.withHeader(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	public static Node withAttribute(final String... attributes) {
		
		final var node = new Node();
		for (var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 */
	public static <BN extends BaseNode> Node withAttributes(final Iterable<BN> attributes) {
		
		final var node = new Node();
		node.addAttributes(attributes);
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link Node} with the given header.
	 */
	public static Node withHeader(final boolean header) {
		
		final var node = new Node();
		node.setHeader(BooleanHelper.toString(header));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link Node} with the given header.
	 */
	public static Node withHeader(final double header) {
		
		final var node = new Node();
		node.setHeader(String.valueOf(header));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link Node} with the given header.
	 */
	public static Node withHeader(final long header) {
		
		final var node = new Node();
		node.setHeader(String.valueOf(header));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link Node} with the given header.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeader(final String header) {
		
		final var node = new Node();
		node.setHeader(header);
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given attribute is null.
	 */
	public static <BN extends BaseNode> Node withHeaderAndAttribute(final String header, final BN attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttribute(attribute);
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attributes is null.
	 */
	@SuppressWarnings("unchecked")
	public static <BN extends BaseNode> Node withHeaderAndAttribute(final String header, final BN... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addAttribute(a);
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final boolean attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttribute(BooleanHelper.toString(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final boolean... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final double attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttribute(String.valueOf(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final double... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final long attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttribute(String.valueOf(attribute));
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final long... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addAttribute(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attribute.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if the given attribute is null.
	 * @throws InvalidArgumentException if the given attribute is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final String attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttribute(attribute);
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attribute
	 * @return a new {@link Node} with the given header and attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws ArgumentIsNullException if one of the given attributes is null.
	 * @throws InvalidArgumentException if one of the given attributes is blank.
	 */
	public static Node withHeaderAndAttribute(final String header, final String... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addAttribute(a);
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @param attributes
	 * @return a new {@link Node} with the given attributes.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public static <BN extends BaseNode> Node withHeaderAndAttributes(
		final String header,
		final Iterable<BN> attributes
	) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addAttributes(attributes);
		
		return node;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private final LinkedList<Node> attributes = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Node} without header and without attributes.
	 */
	public Node() {}
	
	//method
	/**
	 * Adds the given attribute to the current {@link Node}.
	 */
	@Override
	public Node addAttribute(final BaseNode attribute) {
		
		addAttribute(attribute.getCopy());
		
		return this;
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link Node}.
	 */
	public Node addAttribute(final Node attribute) {
		
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link Node}.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(final String attribute) {
		
		//Calls other method
		addAttribute(fromString(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of the current {@link Node}.
	 * Sets the given prefix has header to the current {@link Node} if it does not have a header.
	 * 
	 * @param prefix
	 * @throws ArgumentIsNullException if the given prefix is null.
	 * @throws InvalidArgumentException if the given prefix is blank.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Asserts that the given prefix is not null or blank.
		Validator.assertThat(prefix).thatIsNamed(VariableNameCatalogue.PREFIX).isNotBlank();
		
		//Handles the case that the current Node does not have a header.
		if (!hasHeader()) {
			setHeader(prefix);
		}
		
		//Handles the case that the current Node has a header.
		else {
			setHeader(prefix + getHeader());
		}
	}
	
	//method
	/**
	 * Adds the given postfix to the header of the current {@link Node}.
	 * Sets the given postfix as header to the current {@link Node} if it does not have a header.
	 * 
	 * @param postfix
	 * @throws ArgumentIsNullException if the given postfix is null.
	 * @throws InvalidArgumentArgumentException if the given postfix is blank.
	 */
	public void addPostfixToHeader(final String postfix) {
		
		//Asserts that the given postfix is not null or blank.
		Validator.assertThat(postfix).thatIsNamed(VariableNameCatalogue.POSTFIX).isNotBlank();
		
		//Handles the case that the current Node does not have a header.
		if (hasHeader()) {
			setHeader(postfix);
		}
		
		//Handles the case that the current Node has a header.
		else {
			setHeader(getHeader() + postfix);
		}
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link Node}.
	 */
	public int getAttributesCount() {
		return attributes.getElementCount();
	}

	//method
	/**
	 * @return the header of this specification.
	 * @throws ArgumentDoesNotHaveAttributeException if this specification does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Asserts that the current Node has a header.
		if (!hasHeader()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return header;
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Node}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IContainer<Node> getRefAttributes() {
		return ReadContainer.forIterable(attributes);
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if the current {@link Node} does not contain an attribute with the given header
	 */
	public IContainer<Node> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}

	//method
	/**
	 * @return the one attribute of the current {@link Node}
	 * @throws EmptyArgumentException if the current {@link Node} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link Node} contains several attributes.
	 */
	@Override
	public Node getRefOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 * -the current {@link Node} does not contain an attribute with the given header
	 * -the first attribute of the current {@link Node} with the given header
	 * does not contain an attribute or contains several attributes
	 */
	public Node getRefOneAttributeOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a string representation
	 * of the one attribute of the first attribute with the given header of the current {@link Node}.
	 */
	public String getRefOneAttributeOfFirstAttributeAsString(String header) {
		return getRefOneAttributeOfFirstAttribute(header).toString();
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
	public void removeAttributes() {
		attributes.clear();
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
	public void removeFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		attributes.removeFirst(selector::getOutput);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node removeHeader() {
		
		header = null;
		
		return this;
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
		Validator.assertThat(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current Node.
		this.header = header;
		
		return this;
	}
}
