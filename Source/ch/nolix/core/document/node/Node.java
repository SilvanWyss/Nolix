//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalBooleanHelper;
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
 * A {@link Node} is a {@link BaseNode} that is completely stored in the memory like a common object.
 * 
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
		return Node.withHeaderAndAttribute(pEnum.getClass().getSimpleName(), pEnum.name());
	}
	
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
		
		GlobalValidator.assertThat(intPair).thatIsNamed(IntPair.class).isNotNull();
		
		return withAttribute(intPair.getValue1(), intPair.getValue2());
	}
	
	//static method
	/**
	 * @param node
	 * @return a new {@link Node} from the given {@link INode}.
	 */
	public static Node fromNode(final INode<?> node) {
		
		//TODO: Return the given Node itself as soon as it was made not mutable.
		final var newNode = new Node();
		
		if (node.hasHeader()) {
			newNode.setHeader(node.getHeader());
		}
		
		for (final var cn : node.getRefChildNodes()) {
			newNode.addChildNode(Node.fromNode(cn));
		}
		
		return newNode;
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
	public static <BN extends BaseNode<?>> Node withAttribute(final BN attribute) {
		
		final var node = new Node();
		node.addChildNode(attribute);
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @return a new {@link Node} with the given attributes.
	 */
	@SuppressWarnings("unchecked")
	public static <BN extends BaseNode<?>> Node withAttribute(final BN... attributes) {
		
		final var node = new Node();
		node.addChildNode(attributes);
		
		return node;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static Node withAttribute(final boolean attribute) {
		
		final var node = new Node();
		node.addChildNode(Node.withHeader(String.valueOf(attribute)));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNodeFromString(String.valueOf(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNodeFromString(String.valueOf(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNode(Node.withHeader(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
		}
		
		return node;
	}
	
	//static method
	/**
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @return a new {@link Node} with the given attributes.
	 */
	public static <BN extends BaseNode<?>> Node withAttributes(final Iterable<BN> attributes) {
		
		final var node = new Node();
		node.addChildNodes(attributes);
		
		return node;
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link Node} with the given header.
	 */
	public static Node withHeader(final boolean header) {
		
		final var node = new Node();
		node.setHeader(GlobalBooleanHelper.toString(header));
		
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
	public static <BN extends BaseNode<?>> Node withHeaderAndAttribute(final String header, final BN attribute) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addChildNode(attribute);
		
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
	public static <BN extends BaseNode<?>> Node withHeaderAndAttribute(final String header, final BN... attributes) {
		
		final var node = new Node();
		node.setHeader(header);
		for (final var a : attributes) {
			node.addChildNode(a);
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
		node.addChildNodeFromString(GlobalBooleanHelper.toString(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNodeFromString(String.valueOf(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNodeFromString(String.valueOf(attribute));
		
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
			node.addChildNode(Node.withHeader(a));
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
		node.addChildNodeFromString(attribute);
		
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
			node.addChildNodeFromString(a);
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
	public static <BN extends BaseNode<?>> Node withHeaderAndAttributes(
		final String header,
		final Iterable<BN> attributes
	) {
		
		final var node = new Node();
		node.setHeader(header);
		node.addChildNodes(attributes);
		
		return node;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private final LinkedList<Node> attributes = new LinkedList<>();
	
	//method
	public Node addChildNode(final INode<?>... childNodes) {
		
		for (final var cn : childNodes) {
			attributes.addAtEnd(Node.fromNode(cn));
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
			attributes.addAtEnd(Node.fromNode(cn));
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
	 * {@inheritDoc}
	 */
	@Override
	public int getChildNodeCount() {
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
		return ReadContainer.forIterable(attributes).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getRefSingleChildNode() {
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
	public void removeChildNodes() {
		attributes.clear();
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
	public BaseNode<?> removeAndGetRefFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return attributes.removeAndGetRefFirst(selector::getOutput);
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
	 * {@inheritDoc}
	 */
	@Override
	public void replaceFirstChildNodeWithGivenHeaderByGivenChildNode(
		final String header,
		final INode<?> attribute
	) {
		attributes.replaceFirst(a -> a.hasHeader(header), Node.fromNode(attribute));
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
}
