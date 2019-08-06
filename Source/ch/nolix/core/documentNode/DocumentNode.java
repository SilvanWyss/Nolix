//package declaration
package ch.nolix.core.documentNode;

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
 * A {@link DocumentNode} is a {@link DocumentNodeoid}
 * that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 500
 */
public final class DocumentNode extends DocumentNodeoid implements ISmartObject<DocumentNode> {
	
	//static method
	/**
	 * @param filePath
	 * @return a new {@link DocumentNode} from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException if the file with the given file path does not represent a {@link DocumentNode}.
	 */
	public static DocumentNode createFromFile(final String filePath) {
		
		final var documentNode = new DocumentNode();
		documentNode.resetFromFile(filePath);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link DocumentNode} from the given string.
	 * @throws InvalidArgumentException if the given string does not represent a {@link DocumentNode}.
	 */
	public static DocumentNode createFromString(final String string) {
		
		final var documentNode = new DocumentNode();
		documentNode.reset(string);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param pEnum
	 * @return a new {@link DocumentNode} with a header from the given pEnum.
	 */
	public static DocumentNode createWithHeader(final Enum<?> pEnum) {
		return createWithHeader(pEnum.toString());
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link DocumentNode} with the given header.
	 * @throws NullArgumentException if the given header is null.
	 */
	public static DocumentNode createWithHeader(final String header) {
		
		final var documentNode = new DocumentNode();
		documentNode.setHeader(header);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link DocumentNode} with the given attribute.
	 */
	public static DocumentNode createWithOneAttribute(final boolean attribute) {
		
		final var documentNode = new DocumentNode();
		documentNode.setHeader(String.valueOf(attribute));
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link DocumentNode} with the given attribute.
	 * @throws NullArgumentException if the given attribute is null.
	 */
	public static DocumentNode createWithOneAttribute(final DocumentNodeoid attribute) {
		
		final var documentNode = new DocumentNode();
		documentNode.addAttribute(attribute);
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link DocumentNode} with the given attribute.
	 */
	public static final DocumentNode createWithOneAttribute(final int attribute) {
		
		final var documentNode = new DocumentNode();
		documentNode.addAttribute(String.valueOf(attribute));
		
		return documentNode;
	}
	
	//static method
	/**
	 * @param attribute
	 * @return a new {@link DocumentNode} with the given attribute.
	 * @throws NullArgumentException if the given attribute is null.
	 * @throws InvalidArgumentException with the given attribute.
	 */
	public static DocumentNode createWithOneAttribute(final String attribute) {
		
		final var documentNode = new DocumentNode();
		documentNode.addAttribute(createWithHeader(attribute));
		
		return documentNode;
	}
	
	//optional attribute
	private String header;
	
	//multi-attribute
	private final List<DocumentNode> attributes = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} without header and without attributes.
	 */
	public DocumentNode() {}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with a header that consists of the given character.
	 * 
	 * @param character
	 */
	public DocumentNode(final char character) {
		setHeader(Character.toString(character));
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attribute is not valid.
	 */
	public <S extends DocumentNodeoid> DocumentNode(final Iterable<S> attributes) {
		attributes.forEach(a -> a.addAttribute(a));
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header.
	 * 
	 * @param header
	 */
	public DocumentNode(final long header) {
		setHeader(String.valueOf(header));
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public DocumentNode(final String header) {
		setHeader(header);
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final DocumentNode... attributes) {
		
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
	public DocumentNode(final String header, final Iterable<DocumentNode> attributes) {
		
		//Calls other constructor.
		this(header);
		
		resetAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attribute.
	 * 
	 * @param header
	 * @param attribute
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public DocumentNode(final String header, final boolean attribute) {
		
		//Calls other constructor.
		this(header, String.valueOf(attribute));
	}

	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attribute.
	 * 
	 * @param header
	 * @param attribute
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public DocumentNode(final String header, final long attribute) {
		
		//Calls other constructor.
		this(header, String.valueOf(attribute));
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final String... attributes) {
		
		//Calls other constructor.
		this(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			if (a.isEmpty()) {
				addAttribute(new DocumentNode());
			}
			
			else {
				addAttribute(createWithHeader(a));
			}
		}
	}

	//method
	/**
	 * Adds the given attribute to the current {@link DocumentNode}.
	 */
	@Override
	public void addAttribute(final DocumentNodeoid attribute) {
		addAttribute(attribute.getCopy());
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link DocumentNode}.
	 * 
	 * @param attribute
	 * @throws NullArgumentException if the given attribute is null.
	 */
	public void addAttribute(final DocumentNode attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link DocumentNode}.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(final String attribute) {
		
		//Calls other method
		addAttribute(createFromString(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of the current {@link DocumentNode}.
	 * Sets the given prefix has header to the current {@link DocumentNode} if it does not have a header.
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
	 * Adds the given postfix to the header of the current {@link DocumentNode}.
	 * Sets the given postfix as header to the current {@link DocumentNode} if it does not have a header.
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
	 * @return the number of attributes of the current {@link DocumentNode}.
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
	 * @return the attributes of the current {@link DocumentNode}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IContainer<DocumentNode> getRefAttributes() {
		return new ReadContainer<>(attributes);
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if the current {@link DocumentNode} does not contain an attribute with the given header
	 */
	public IContainer<DocumentNode> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}

	//method
	/**
	 * @return the one attribute of the current {@link DocumentNode}
	 * @throws EmptyArgumentException if the current {@link DocumentNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link DocumentNode} contains several attributes.
	 */
	@Override
	public DocumentNode getRefOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 * -the current {@link DocumentNode} does not contain an attribute with the given header
	 * -the first attribute of the current {@link DocumentNode} with the given header
	 * does not contain an attribute or contains several attributes
	 */
	public DocumentNode getRefOneAttributeOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a string representation
	 * of the one attribute of the first attribute with the given header of the current {@link DocumentNode}.
	 */
	public String getRefOneAttributeOfFirstAttributeAsString(String header) {
		return getRefOneAttributeOfFirstAttribute(header).toString();
	}
	
	//method
	/**
	 * @return true if the current {@link DocumentNode} has a header
	 */
	@Override
	public boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Removes the attributes of the current {@link DocumentNode}.
	 */
	@Override
	public void removeAttributes() {
		attributes.clear();
	}

	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link DocumentNode}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if the current {@link DocumentNode} does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<DocumentNodeoid> selector) {
		attributes.removeFirst(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * Removes the header of the current {@link DocumentNode}.
	 */
	@Override
	public void removeHeader() {
		header = null;
	}
	
	//method
	/**
	 * Sets the header of the current {@link DocumentNode}.
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
