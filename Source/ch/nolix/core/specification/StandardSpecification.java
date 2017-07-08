//package declaration
package ch.nolix.core.specification;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A standard specification is a specification that is completely stored in the memory
 * like as normal objects, but probably not like as other specifications.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
 */
public final class StandardSpecification extends Specification {
	
	//codes
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	public static final String DOT_CODE = "$D";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SIGN_CODE = "$L";
	
	//static mehtod
	public static final StandardSpecification loadSpecification(final String path) {
		final StandardSpecification standardSpecification = new StandardSpecification();
		standardSpecification.loadFromFile(path);
		return standardSpecification;
	}
	
	//optional attribute
	private String header;
	
	//multiple attribute
	private final List<StandardSpecification> attributes = new List<StandardSpecification>();
	
	//constructor
	/**
	 * Creates new specification without header and without attributes.
	 */
	public StandardSpecification() {}
		
	//constructor
	/**
	 * Creates new specification the given string represents.
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string represents no standard specification.
	 */
	public StandardSpecification(String string) {
		setValue(string);		
	}
	
	//constructor
	/**
	 * Creates new specification with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws Exception if:
	 *  -the given header is null or an empty string
	 *  -the given attributes are null
	 */
	public StandardSpecification(String header, Iterable<StandardSpecification> attributes) {
		setHeader(header);
		setAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates new specification with the given header and attributes.
	 * 
	 * @param header
	 * @param attributes
	 * @throws Exception if
	 *  -the given header is null or an empty string
	 *  -the given attributes are not valid
	 */
	public StandardSpecification(String header, String... attributes) {
		
		setHeader(header);
		
		for (String a: attributes) {
			addAttribute(a);
		}
	}
	
	//method
	/**
	 * Loads this specification from the file with the given file path.
	 * 
	 * @param filePath
	 * @throws Exception if:
	 *  -the given file path is not valid
	 *   -the file with the given file path is not valid
	 */
	public void loadFromFile(String filePath) {
		try {
			setValue(new String(Files.readAllBytes(Paths.get(filePath))).replace("\n", "").replace("\t", ""));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//default method
	/**
	 * Saves this specification to the file with the given file path.
	 * 
	 * @param filePath
	 * @throws Exception if:
	 *  -the given file path is not valid
	 *  -an other error occurs
	 */
	public void saveToFile(String filePath) {
		try (PrintWriter printWriter = new PrintWriter(filePath)) {
			printWriter.print(toFormatedReproducingString());
			printWriter.flush();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void addAttribute(final Specification attribute) {
		// TODO Auto-generated method stub
		
	}
	
	//method
	/**
	 * Adds the given attribute to this standard specification.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is null
	 */
	public void addAttribute(StandardSpecification attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attribute to this standard specification.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(String attribute) {
		addAttribute(new StandardSpecification(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of this standard specification.
	 * Sets the given prefix has header to this standard specification if it has no header.
	 * 
	 * @param prefix
	 * @throws NullArgumentException if the given prefix is null.
	 * @throws EmptyArgumentException if the given prefix is empty.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Checks if the given prefix is not null or empty.
		Validator.supposeThat(prefix).thatIsNamed("prefix").isNotEmpty();
		
		//Handles the case if this standard specification has no header.
		if (hasHeader()) {
			setHeader(prefix);
		}
		
		//Handles the case if this standard specification has a header.
		else {			
			setHeader(prefix + getHeader());
		}
	}
	
	//method
	/**
	 * Adds the given postfix to the header of this standard specification.
	 * Sets the given postfix as header to this standard specification if it has no header.
	 * 
	 * @param postfix
	 * @throws NullArgumentException if the given postfix is null.
	 * @throws EmptyArgumentException if the given postfix is empty.
	 */
	public void addPostfixToHeader(String postfix) {
		
		//Checks if the given postfix is not null or empty.
		Validator.supposeThat(postfix).thatIsNamed("postfix").isNotEmpty();
		
		//Handles the case if this standard specification has no header.
		if (hasHeader()) {
			setHeader(postfix);			
		}
		
		//Handles the case if this standard specification has a header.
		else {
			setHeader(getHeader() + postfix);
		}
	}
	
	//method
	/**
	 * @return true if this standard specification contains attributes
	 */
	public boolean containsAttributes() {
		return !getRefAttributes().isEmpty();
	}
	
	//method
	/**
	 * @return true if this standard specification contains exactly 1 attribute
	 */
	public boolean containsOneAttribute() {
		return getRefAttributes().containsOne();
	}
	
	//method
	/**
	 * @return true if this standard specification equals the given object.
	 */
	public boolean equals(final Object object) {
		
		//Handles the case if the given object is no standard specification.
		if (!(object instanceof StandardSpecification)) {
			return false;
		}
		
		//Handles the case if the given object is a standard specification.
		
			final StandardSpecification standardSpecification = (StandardSpecification)object;
			
			if (!hasHeader()) {
				if (standardSpecification.hasHeader()) {
					return false;
				}
			}
			else {
				if (!standardSpecification.hasHeader(getHeader())) {
					return false;
				}
			}
			
			if (getAttributeCount() != standardSpecification.getAttributeCount()) {
				return false;
			}
			
			final Iterator<StandardSpecification> iterator
			= standardSpecification.getRefAttributes().iterator();
			
			//Iterates the attributes of this standard specification.
			for (final StandardSpecification a : getRefAttributes()) {
				if (!a.equals(iterator.next())) {
					return false;
				}
			}
			
			return true;
	}
	
	//method
	/**
	 * @return the number of attributes of this standard specification.
	 */
	public int getAttributesCount() {
		return attributes.getElementCount();
	}
	
	//method
	/**
	 * @return a copy of this specification.
	 */
	public StandardSpecification getCopy() {
		return new StandardSpecification(getHeader(), getRefAttributes().to(a -> a.getCopy()));
	}

	//method
	/**
	 * @return the header of this specification.
	 * @throws UnexistingAttributeException if this specification has no header.
	 */
	public String getHeader() {
		
		if (!hasHeader()) {
			throw new UnexistingAttributeException(this, "header");
		}
		
		return header;
	}
	
	//method
	/**
	 * @return the boolean the one attribute of this standard specification represents
	 * @throws Exception if:
	 *  -this standard specification contains no or several attributes
	 *  -the one attribute of this standard specification does not represent a boolean
	 */
	public boolean getOneAttributeToBoolean() {
		return getRefOneAttribute().toBoolean();
	}
	
	//method
	public double getOneAttributeToDouble() {
		return getRefOneAttribute().toDouble();
	}
	
	//method
	/**
	 * @return the integer the one attribute of this standard specification represents
	 * @throws Exception if:
	 *  -this standard specification contains no or several attributes
	 *  -the one attribute of this standard specification does not represent an integer
	 */
	public int getOneAttributeToInteger() {
		return getRefOneAttribute().toInt();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of this standard specification
	 * @throws Exception if this standard specification contains no or several attributes
	 */
	public String getOneAttributeToString() {
		return getRefOneAttribute().toString();
	}
	
	//method
	/**
	 * @return the one attribute of this standard specification
	 * @throws Exception if this standard specification has no or several attributes
	 */
	@SuppressWarnings("unchecked")
	public StandardSpecification getRefOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -this standard specification contains no attribute with the given header
	 *  -the first attribute of this standard specification with the given header contains no or several attributes
	 */
	public StandardSpecification getRefOneAttributeOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a string representation of the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -this standard specification contains no attribute with the given header
	 *  -the first attribute of this standard specification with the given header contains no or several attributes
	 */
	public String getRefOneAttributeOfFirstAttributeToString(String header)  {
		return getRefOneAttributeOfFirstAttribute(header).toString();
	}
	
	//method
	/**
	 * @return the attributes of this standard specification
	 */
	@SuppressWarnings("unchecked")
	public AccessorContainer<StandardSpecification> getRefAttributes() {
		return new AccessorContainer<StandardSpecification>(attributes);
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if this standard specification contains no attribute with the given header
	 */
	public AccessorContainer<StandardSpecification> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}
	
	//method
	/**
	 * @return true if this standard specification has a header
	 */
	public boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Sets the attributes of this standard specification.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are null
	 */
	public void setAttributes(final Iterable<StandardSpecification> attributes) {
		
		this.attributes.clear();
		
		this.attributes.addAtEnd(attributes);
	}
	
	//method
	/**
	 * Sets the header of this standard specification.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 */
	public void setHeader(final String header) {
		
		//Checks if the given header is not null or empty.
		Validator.supposeThat(header).thatIsNamed(header).isNotEmpty();
		
		this.header
		= header
		.replace(DOT_CODE, ".")
		.replace(OPEN_BRACKET_CODE, "(")
		.replace(CLOSED_BRACKET_CODE, ")");
	}
	
	//method
	/**
	 * @return a string representation of this standard specification
	 */
	public String toReproducingString() {
		String string = StringManager.EMPTY_STRING;
		
		//Handles header if this specification has a header.
		if (hasHeader()) {
			string += getHeader().replace(".", DOT_CODE).replace("(", OPEN_BRACKET_CODE).replace(")", CLOSED_BRACKET_CODE);
		}
		
		//Handles attributes.
		if (containsAttributes()) {
			string += CharacterManager.OPENING_BRACKET;
			boolean begin = true;
			for (StandardSpecification a: attributes) {
				if (begin) {
					begin = false;
				}
				else {
					string += CharacterManager.COMMA;
				}
				string += a.toReproducingString();
			}
			string += CharacterManager.CLOSING_BRACKET;
		}
		return string;
	}
	
	//method
	/**
	 * @return a string representation of this object in quotes
	 */
	public String toStringInQuotes() {
		return ("'" + toString() + "'");
	}
	
	//method
	/**
	 * Sets the value of this standard specification.
	 * 
	 * @param value
	 * @throws InvalidValueException if the given value is not valid.
	 */
	private void setValue(final String value) {
		
		//Extracts the header and the start index of the attributes.
		boolean hasAttributes = false;
		int startIndex = 0;
		for (int i = 0; i < value.length(); i++) {
			
			final Character character = value.charAt(i);
			
			//Checks whether the given specification string contains a closing bracket before an opening bracket.
			if (character == CharacterManager.CLOSING_BRACKET) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
			
			//Checks whether the given specification string contains a comma before an opening bracket.
			if (character == CharacterManager.COMMA) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
			
			if (character == CharacterManager.OPENING_BRACKET) {	
				hasAttributes = true;
				if (i > 0) {
					setHeader(value.substring(0, i));
				}
				startIndex = i + 1;
				break;
			}
		}	
		
		//Extract attributes.
		if (hasAttributes) {

			//Checks whether the start index is too big.
			if (startIndex > value.length() - 1) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
			
			int level = 0;
			String attributeString = StringManager.EMPTY_STRING;
			for (int i = startIndex; i < value.length() - 1; i++)
			{
				char character = value.charAt(i);
				if (character == CharacterManager.DOT && level == 0) {
					attributes.clear();
					setHeader(value);
					return;
				}
				if (character == CharacterManager.OPENING_BRACKET) {
					level++;
				}
				else if (character == CharacterManager.CLOSING_BRACKET) {
					level--;
				}
				if (character == CharacterManager.COMMA && level == 0) {
					attributes.addAtEnd(new StandardSpecification(attributeString));
					attributeString = StringManager.EMPTY_STRING;
				}
				else {
					attributeString += character;
				}
			}
			attributes.addAtEnd(new StandardSpecification(attributeString));
			
			//Checks whether the given standard specification string has not as many opening brackets as closing brackets.
			if (level != 0) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
			
			//Checks whether the last character of the given standard specification string is a closing bracket.
			if (value.charAt(value.length() - 1) != CharacterManager.CLOSING_BRACKET) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
		}
		else if (value.length() > 0) {
			setHeader(value);
		}
	}
}
