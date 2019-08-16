//package declaration
package ch.nolix.core.node;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.containers.ReadContainer;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link Node} is a {@link BaseNode}
 * that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 490
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
		
		final var documentNode = new Node();
		documentNode.resetFromFile(filePath);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link Node} from the given string.
	 * @throws InvalidArgumentException if the given string does not represent a {@link Node}.
	 */
	public static Node fromString(final String string) {
		
		final var documentNode = new Node();
		documentNode.reset(string);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static Node withOneAttribute(final boolean attribute) {
		
		final var documentNode = new Node();
		documentNode.setHeader(String.valueOf(attribute));
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 * @throws NullArgumentException if the given attribute is null.
	 */
	public static Node withOneAttribute(final BaseNode attribute) {
		
		final var documentNode = new Node();
		documentNode.addAttribute(attribute);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 */
	public static final Node withOneAttribute(final int attribute) {
		
		final var documentNode = new Node();
		documentNode.addAttribute(String.valueOf(attribute));
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link Node} with the given attribute.
	 * @throws NullArgumentException if the given attribute is null.
	 * @throws InvalidArgumentException with the given attribute.
	 */
	public static Node withOneAttribute(final String attribute) {
		
		final var documentNode = new Node();
		documentNode.addAttribute(new Node(attribute));
		
		return documentNode;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private final List<Node> attributes = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link Node} without header and without attributes.
	 */
	public Node() {}
	
	//constructor
	/**
	 * Creates a new {@link Node} with a header that consists of the given character.
	 * 
	 * @param character
	 */
	public Node(final char character) {
		setHeader(Character.toString(character));
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with a header that consists of the {@link String} representation of the given pEnum.
	 * 
	 * @param pEnum
	 * @throws NullArgumentException if the given pEnum is null.
	 */
	public Node(final Enum<?> pEnum) {
		
		Validator.suppose(pEnum).thatIsNamed(VariableNameCatalogue.ENUM).isNotNull();
		
		setHeader(pEnum.toString());
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attribute is not valid.
	 */
	public <S extends BaseNode> Node(final Iterable<S> attributes) {
		attributes.forEach(a -> a.addAttribute(a));
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header.
	 * 
	 * @param header
	 */
	public Node(final long header) {
		setHeader(String.valueOf(header));
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public Node(final String header) {
		setHeader(header);
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public Node(final String header, final Node... attributes) {
		
		//Calls other constructor.
		this(header);
		
		addAttribute(attributes);
	}

	//constructor
	/**
	 * Creates a new specification with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public Node(final String header, final Iterable<Node> attributes) {
		
		//Calls other constructor.
		this(header);
		
		resetAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header and attribute.
	 * 
	 * @param header
	 * @param attribute
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public Node(final String header, final boolean attribute) {
		
		//Calls other constructor.
		this(header, String.valueOf(attribute));
	}

	//constructor
	/**
	 * Creates a new {@link Node} with the given header and attribute.
	 * 
	 * @param header
	 * @param attribute
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public Node(final String header, final long attribute) {
		
		//Calls other constructor.
		this(header, String.valueOf(attribute));
	}
	
	//constructor
	/**
	 * Creates a new {@link Node} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public Node(final String header, final String... attributes) {
		
		//Calls other constructor.
		this(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			if (a.isEmpty()) {
				addAttribute(new Node());
			}
			
			else {
				addAttribute(new Node(a));
			}
		}
	}

	//method
	/**
	 * Adds the given attribute to the current {@link Node}.
	 */
	@Override
	public void addAttribute(final BaseNode attribute) {
		addAttribute(attribute.getCopy());
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link Node}.
	 * 
	 * @param attribute
	 * @throws NullArgumentException if the given attribute is null.
	 */
	public void addAttribute(final Node attribute) {
		attributes.addAtEnd(attribute);
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
	 * @throws NullArgumentException if the given prefix is null.
	 * @throws InvalidArgumentException if the given prefix is blank.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Checks if the given prefix is not null or blank.
		Validator.suppose(prefix).thatIsNamed(VariableNameCatalogue.PREFIX).isNotBlank();
		
		//Handles the case that the current DocumentNode does not have a header.
		if (!hasHeader()) {
			setHeader(prefix);
		}
		
		//Handles the case that the current DocumentNode has a header.
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
	 * @throws NullArgumentException if the given postfix is null.
	 * @throws InvalidArgumentArgumentException if the given postfix is blank.
	 */
	public void addPostfixToHeader(final String postfix) {
		
		//Checks if the given postfix is not null or blank.
		Validator.suppose(postfix).thatIsNamed(VariableNameCatalogue.POSTFIX).isNotBlank();
		
		//Handles the case that the current DocumentNode does not have a header.
		if (hasHeader()) {
			setHeader(postfix);
		}
		
		//Handles the case that the current DocumentNode has a header.
		else {
			setHeader(getHeader() + postfix);
		}
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link Node}.
	 */
	public int getAttributesCount() {
		return attributes.getSize();
	}

	//method
	/**
	 * @return the header of this specification.
	 * @throws ArgumentMissesAttributeException if this specification does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Checks if the current DocumentNode has a header.
		if (!hasHeader()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.HEADER);
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
		return new ReadContainer<>(attributes);
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
		attributes.removeFirst(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * Removes the header of the current {@link Node}.
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
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public void setHeader(final String header) {
		
		//Checks if the given header is not null or blank.
		Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current DocumentNode.
		this.header = header;
	}
}
