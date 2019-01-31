//package declaration
package ch.nolix.core.documentNode;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;

//class
/**
 * A {@link DocumentNode} is a {@link DocumentNodeoid}
 * that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 410
 */
public final class DocumentNode extends DocumentNodeoid
implements ISmartObject<DocumentNode> {
	
	//static method
	/**
	 * @param filePath
	 * @return a new {@link DocumentNode} from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException if the file with the given file path does not represent a {@link DocumentNode}.
	 */
	public static DocumentNode createFromFile(final String filePath) {
		
		final var specification = new DocumentNode();
		specification.resetFromFile(filePath);
		
		return specification;
	}
	
	//static method
	/**
	 * @param value
	 * @return a new {@link DocumentNode} from the given value.
	 */
	public static DocumentNode createFromLong(final long value) {
		return new DocumentNode(String.valueOf(value));
	}
	
	//static method
	/**
	 * @param header
	 * @return a new {@link DocumentNode} that consists of the given header.
	 * @throws NullArgumentException if the given header is null.
	 */
	public static final DocumentNode createSpecificationWithHeader(final String header) {
		final DocumentNode specification = new DocumentNode();
		specification.setHeader(header);
		return specification;
	}
	
	//optional attribute
	private String header;
	
	//multiple attribute
	private final List<DocumentNode> attributes = new List<DocumentNode>();
	
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
	 * Creates a new {@link DocumentNode} the given string represents.
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string does not represent a standard specification.
	 */
	public DocumentNode(final String string) {
		reset(string);
	}
	
	//constructor
	/**
	 * Creates a new specification with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final Iterable<DocumentNode> attributes) {
		setHeader(header);
		setAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final List<String> attributes) {
		
		//Sets the header of the current {@link StandardSpecification}.
		setHeader(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			//Adds the current attribute to the current {@link StandardSpecification}.
			addAttribute(createSpecificationWithHeader(a));
		}
	}
	
	//constructor
	/**
	 * Creates a new standard specificatio nwith the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final DocumentNode... attributes) {
		setHeader(header);
		addAttribute(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link DocumentNode} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public DocumentNode(final String header, final String... attributes) {
		
		//Sets the header of the current {@link StandardSpecification}.
		setHeader(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			//Adds the current attribute to the current {@link StandardSpecification}.
			addAttribute(createSpecificationWithHeader(a));
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
	public void addAttribute(DocumentNode attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link DocumentNode}.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(String attribute) {
		addAttribute(new DocumentNode(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of the current {@link DocumentNode}.
	 * Sets the given prefix has header to the current {@link DocumentNode} if it does not have a header.
	 * 
	 * @param prefix
	 * @throws NullArgumentException if the given prefix is null.
	 * @throws EmptyArgumentException if the given prefix is empty.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Checks if the given prefix is not null or empty.
		Validator.suppose(prefix).thatIsNamed("prefix").isNotEmpty();
		
		//Handles the case that the current {@link StandardSpecification} does not have a header.
		if (!hasHeader()) {
			setHeader(prefix);
		}
		
		//Handles the case that the current {@link StandardSpecification} has a header.
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
	 * @throws EmptyArgumentException if the given postfix is empty.
	 */
	public void addPostfixToHeader(String postfix) {
		
		//Checks if the given postfix is not null or empty.
		Validator.suppose(postfix).thatIsNamed("postfix").isNotEmpty();
		
		//Handles the case that the current {@link StandardSpecification} does not have a header.
		if (hasHeader()) {
			setHeader(postfix);
		}
		
		//Handles the case that the current {@link StandardSpecification} has a header.
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
		
		//Checks if the current {@link StandardSpecification} has a header.
		supposeHasHeader();
		
		return header;
	}
	
	//method
	/**
	 * @return the attributes of the current {@link DocumentNode}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IContainer<DocumentNode> getRefAttributes() {
		return new ReadContainer<DocumentNode>(attributes);
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
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if the current {@link DocumentNode} does not contain an attribute with the given header
	 */
	public IContainer<DocumentNode> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
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
	 * Sets the attributes of the current {@link DocumentNode}.
	 * 
	 * @param attributes
	 * @throws NullArgumentException if the given attribute container is null.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public void setAttributes(final Iterable<DocumentNode> attributes) {
		
		this.attributes.clear();
		
		this.attributes.addAtEnd(attributes);
	}
	
	//method
	/**
	 * Sets the header of the current {@link DocumentNode}.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 */
	@Override
	public void setHeader(final String header) {
		
		//Checks if the given header is not null or empty.
		Validator.suppose(header).thatIsNamed(header).isNotEmpty();
		
		this.header
		= header
		.replace(COMMA_CODE, ",")
		.replace(OPEN_BRACKET_CODE, "(")
		.replace(CLOSED_BRACKET_CODE, ")");
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
	 * Removes the header of the current {@link DocumentNode}.
	 */
	@Override
	public void removeHeader() {
		header = null;
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link DocumentNode} does not have a header.
	 */
	private void supposeHasHeader() {
		
		//Checks if the current standard specification has a header.
		if (!hasHeader()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.HEADER);
		}
	}
}
