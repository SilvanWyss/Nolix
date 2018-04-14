//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A {@link StandardSpecification} is a specification
 * that is completely stored in the memory like a common object.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 440
 */
public final class StandardSpecification extends Specification {
	
	//static method
	/**
	 * @param filePath
	 * @return a new {@link StandardSpecification} from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException if the file with the given file path represents no {@link StandardSpecification}.
	 */
	public static StandardSpecification createFromFile(final String filePath) {
		
		final var specification = new StandardSpecification();
		specification.resetFromFile(filePath);
		
		return specification;
	}
	
	//static method
	/**
	 * Creates a new {@link StandardSpecification} that consists of the given header.
	 * 
	 * @param header
	 * @return a new {@link StandardSpecification} that consists of the given header.
	 * @throws NullArgumentException if the given header is null.
	 */
	public static final StandardSpecification createSpecificationWithHeaderOnly(final String header) {
		final StandardSpecification specification = new StandardSpecification();
		specification.setHeader(header);
		return specification;
	}
	
	//optional attribute
	private String header;
	
	//multiple attribute
	private final List<StandardSpecification> attributes = new List<StandardSpecification>();
	
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} without header and without attributes.
	 */
	public StandardSpecification() {}
	
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} with a header that consists of the given character.
	 * 
	 * @param character
	 */
	public StandardSpecification(final char character) {
		setHeader(Character.toString(character));
	}
	
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attribute is not valid.
	 */
	public <S extends Specification> StandardSpecification(final Iterable<S> attributes) {
		attributes.forEach(a -> a.addAttribute(a));
	}
		
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} the given string represents.
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string represents no standard specification.
	 */
	public StandardSpecification(final String string) {
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
	public StandardSpecification(final String header, final Iterable<StandardSpecification> attributes) {
		setHeader(header);
		setAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public StandardSpecification(final String header, final List<String> attributes) {
		
		//Sets the header of the current {@link StandardSpecification}.
		setHeader(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			//Adds the current attribute to the current {@link StandardSpecification}.
			addAttribute(createSpecificationWithHeaderOnly(a));
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
	public StandardSpecification(final String header, final StandardSpecification... attributes) {	
		setHeader(header);
		addAttribute(attributes);
	}
	
	//constructor
	/**
	 * Creates a new {@link StandardSpecification} with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public StandardSpecification(final String header, final String... attributes) {
		
		//Sets the header of the current {@link StandardSpecification}.
		setHeader(header);
		
		//Iterates the given attributes.
		for (final String a : attributes) {
			
			//Adds the current attribute to the current {@link StandardSpecification}.
			addAttribute(createSpecificationWithHeaderOnly(a));
		}
	}

	//method
	/**
	 * Adds the given attribute to the current {@link StandardSpecification}.
	 */
	public void addAttribute(final Specification attribute) {
		addAttribute(attribute.createCopy());
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link StandardSpecification}.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is null
	 */
	public void addAttribute(StandardSpecification attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link StandardSpecification}.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(String attribute) {
		addAttribute(new StandardSpecification(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of the current {@link StandardSpecification}.
	 * Sets the given prefix has header to the current {@link StandardSpecification} if it has no header.
	 * 
	 * @param prefix
	 * @throws NullArgumentException if the given prefix is null.
	 * @throws EmptyArgumentException if the given prefix is empty.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Checks if the given prefix is not null or empty.
		Validator.suppose(prefix).thatIsNamed("prefix").isNotEmpty();
		
		//Handles the case that the current {@link StandardSpecification} has no header.
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
	 * Adds the given postfix to the header of the current {@link StandardSpecification}.
	 * Sets the given postfix as header to the current {@link StandardSpecification} if it has no header.
	 * 
	 * @param postfix
	 * @throws NullArgumentException if the given postfix is null.
	 * @throws EmptyArgumentException if the given postfix is empty.
	 */
	public void addPostfixToHeader(String postfix) {
		
		//Checks if the given postfix is not null or empty.
		Validator.suppose(postfix).thatIsNamed("postfix").isNotEmpty();
		
		//Handles the case that the current {@link StandardSpecification} has no header.
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
	 * @return the number of attributes of the current {@link StandardSpecification}.
	 */
	public int getAttributesCount() {
		return attributes.getElementCount();
	}

	//method
	/**
	 * @return the header of this specification.
	 * @throws UnexistingAttributeException if this specification has no header.
	 */
	public String getHeader() {
		
		//Checks if the current {@link StandardSpecification} has a header.
		supposeHasHeader();
		
		return header;
	}
	
	//method
	/**
	 * @return the attributes of the current {@link StandardSpecification}
	 */
	@SuppressWarnings("unchecked")
	public IContainer<StandardSpecification> getRefAttributes() {
		return new ReadContainer<StandardSpecification>(attributes);
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link StandardSpecification}
	 * @throws EmptyStateException if the current {@link StandardSpecification} contains no attributes.
	 * @throws InvalidStateException if the current {@link StandardSpecification} contains several attributes.
	 */
	public StandardSpecification getRefOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -the current {@link StandardSpecification} contains no attribute with the given header
	 *  -the first attribute of the current {@link StandardSpecification} with the given header contains no or several attributes
	 */
	public StandardSpecification getRefOneAttributeOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a string representation of the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -the current {@link StandardSpecification} contains no attribute with the given header
	 *  -the first attribute of the current {@link StandardSpecification} with the given header contains no or several attributes
	 */
	public String getRefOneAttributeOfFirstAttributeToString(String header)  {
		return getRefOneAttributeOfFirstAttribute(header).toString();
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if the current {@link StandardSpecification} contains no attribute with the given header
	 */
	public IContainer<StandardSpecification> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}
	
	//method
	/**
	 * @return true if the current {@link StandardSpecification} has a header
	 */
	public boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link StandardSpecification}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if the current {@link StandardSpecification} contains no attribute the given selector selects.
	 */
	public void removeFirstAttribute(final IElementTakerBooleanGetter<Specification> selector) {
		attributes.removeFirst(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * Sets the attributes of the current {@link StandardSpecification}.
	 * 
	 * @param attributes
	 * @throws NullArgumentException if the given attribute container is null.
	 * @throws NullArgumentException if one of the given attributes is null.
	 */
	public void setAttributes(final Iterable<StandardSpecification> attributes) {
		
		this.attributes.clear();
		
		this.attributes.addAtEnd(attributes);
	}
	
	//method
	/**
	 * Sets the header of the current {@link StandardSpecification}.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 */
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
	 * Removes the attributes of the current {@link StandardSpecification}.
	 */
	public void removeAttributes() {
		attributes.clear();
	}

	//method
	/**
	 * Removes the header of the current {@link StandardSpecification}.
	 */
	public void removeHeader() {
		header = null;
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link StandardSpecification} has no header.
	 */
	private void supposeHasHeader() {
		
		//Checks if the current standard specification has a header.
		if (!hasHeader()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.HEADER);
		}
	}
}
