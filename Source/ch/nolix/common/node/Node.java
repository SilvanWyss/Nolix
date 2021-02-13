//package declaration
package ch.nolix.common.node;

//own imports
import ch.nolix.common.commontypehelper.BooleanHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.functionapi.IElementTakerBooleanGetter;
import ch.nolix.common.generalskillapi.ISmartObject;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.common.pair.IntPair;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Node} is a {@link BaseNode} that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 610
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
	 * @throws UnrepresentingArgumentException if the given string does not represent a {@link Node}.
	 */
	public static Node fromString(final String string) {
		
		final var node = new Node();
		node.resetFromString(string);
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @param <BN> is the type of the given attribute.
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
	 * @param <BN> is the type of the given attributes.
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
	 * @param <BN> is the type of the given attributes.
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
	 * @param <BN> is the type of the given attributes.
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
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
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
	 * @param attributes
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
	 * @param attributes
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
	 * @param attributes
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
	 * @param attributes
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
	 * @param <BN> is the type of the given attributes.
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
	 * Creates a new {@link Node}.
	 */
	public Node() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node addAttribute(final BaseNode attribute) {
		
		attributes.addAtEnd(attribute.getCopy());
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAttributeCount() {
		return attributes.getElementCount();
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
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.HEADER);
		}
		
		return header;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<BaseNode> getRefAttributes() {
		return ReadContainer.forIterable(attributes).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getRefOneAttribute() {
		return attributes.getRefOne();
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
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current Node.
		this.header = header;
		
		return this;
	}
}
