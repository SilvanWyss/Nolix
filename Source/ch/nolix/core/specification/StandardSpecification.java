/*
 * file:	Specification.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	170
 */

//package declaration
package ch.nolix.core.specification;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator.Validator;

//class
public final class StandardSpecification extends Specification {
	
	//static mehtod
	public static final StandardSpecification loadSpecification(final String path) {
		final StandardSpecification standardSpecification = new StandardSpecification();
		standardSpecification.loadFromFile(path);
		return standardSpecification;
	}
	
	//codes
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	public static final String DOT_CODE = "$D";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SIGN_CODE = "$L";
	
	//optional attribute
	private String header;
	
	//multiple attribute
	private List<StandardSpecification> attributes = new List<StandardSpecification>();
	
	//constructor
	/**
	 * Creates new specification without header and without attributes.
	 */
	public StandardSpecification() {}
	
	public StandardSpecification(Object object) {
		this(object.toString());
	}
		
	//constructor
	/**
	 * Creates new specification the given string represents.
	 * 
	 * @param string
	 * @throws Exception if the given string is not valid
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
	
	public final boolean equals(Object object) {

		if (object == null) {
			return false;
		}
		
		if (!(object instanceof StandardSpecification)) {
			return false;
		}
		
		return object.toString().equals(toString());
	}
	
	//method
	/**
	 * @return a clone of this specification
	 */
	public final StandardSpecification getClone() {
		return new StandardSpecification(getHeader(), getRefAttributes().to(a -> a.getClone()));
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
	public final void loadFromFile(String filePath) {
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
	public final void saveToFile(String filePath) {
		try (PrintWriter printWriter = new PrintWriter(filePath)) {
			printWriter.print(toFormatedReproducingString());
			printWriter.flush();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//method
	/**
	 * @return a formated string representation of this object
	 */
	public final String toFormatedReproducingString() {
		return toFormatedReproducingString(0);
	}
	
	//method
	/**
	 * Adds the given attribute to this specificationoid.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is null
	 */
	public void addAttribute(StandardSpecification attribute) {
		attributes.addAtEnd(attribute);
	}
	
	//method
	/**
	 * Adds the given attribute to this specificationoid.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addAttribute(String attribute) {
		addAttribute(new StandardSpecification(attribute));
	}
	
	//method
	/**
	 * Adds the given prefix to the header of this specificationoid.
	 * 
	 * @param prefix
	 * @throws Exception if the given prefix is null or an empty string
	 */
	public final void addPrefixToHeader(String prefix) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty("prefix", prefix);
		
		if (hasHeader()) {
			setHeader(prefix + getHeader());
		}
		else {
			setHeader(prefix);
		}
	}
	
	//method
	/**
	 * Adds the given postfix to the header of this specificationoid.
	 * 
	 * @param postfix
	 * @throws Exception if the given postfix is null or an empty string
	 */
	public final void addPostfixToHeader(String postfix) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty("postfix", postfix);
		
		if (hasHeader()) {
			setHeader(getHeader() + postfix);
		}
		else {
			setHeader(postfix);
		}
	}

	//method
	/**
	 * @return true if all attributes of this specificationoid have no attributes
	 */
	public boolean allAttributesHaveNoAttributes() {
		return !attributes.contains(s -> s.containsAttributes());
	}
	
	//method
	/**
	 * @return true if this specificationoid consists only of a header
	 */
	public final boolean consistsOfHeaderOnly() {
		return (hasHeader() && !containsAttributes());
	}
	
	//method
	/**
	 * @param header
	 * @return true if htis specificationoid consists only of the given header
	 */
	public final boolean consistsOfHeader(final String header) {
		return (hasHeader(header) && !containsAttributes());
	}
	
	//method
	/**
	 * @return true if this specificationoid contains attributes
	 */
	public final boolean containsAttributes() {
		return !attributes.isEmpty();
	}
	
	//method
	/**
	 * @return true if this specificationoid contains exactly 1 attribute
	 */
	public final boolean containsOneAttribute() {
		return attributes.containsOne();
	}
	
	//method
	/**
	 * @return the number of attributes of this specificationoid
	 */
	public final int getAttributesCount() {
		return attributes.getElementCount();
	}

	//method
	/**
	 * @return the header of this specificationoid
	 * @throws UnexistingAttributeException if this specificationoid has no header
	 */
	public final String getHeader() {
		
		if (!hasHeader()) {
			throw new UnexistingAttributeException(this, "header");
		}
		
		return header;
	}
	
	//method
	/**
	 * @return the boolean the one attribute of this specificationoid represents
	 * @throws Exception if:
	 *  -this specificationoid contains no or several attributes
	 *  -the one attribute of this specificationoid does not represent a boolean
	 */
	public final boolean getOneAttributeToBoolean() {
		return getRefOneAttribute().toBoolean();
	}
	
	//method
	public final double getOneAttributeToDouble() {
		return getRefOneAttribute().toDouble();
	}
	
	//method
	/**
	 * @return the integer the one attribute of this specificationoid represents
	 * @throws Exception if:
	 *  -this specificationoid contains no or several attributes
	 *  -the one attribute of this specificationoid does not represent an integer
	 */
	public final int getOneAttributeToInteger() {
		return getRefOneAttribute().toInteger();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of this specificationoid
	 * @throws Exception if this specificationoid contains no or several attributes
	 */
	public final String getOneAttributeToString() {
		return getRefOneAttribute().toString();
	}
	
	//method
	/**
	 * @return the one attribute of this specificationoid
	 * @throws Exception if this specificationoid has no or several attributes
	 */
	public final StandardSpecification getRefOneAttribute() {
		return attributes.getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -this specificationoid contains no attribute with the given header
	 *  -the first attribute of this specificationoid with the given header contains no or several attributes
	 */
	public final StandardSpecification getRefOneAttributeOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a string representation of the one attribute of the first attribute with the given header
	 * @throws Exception if:
	 *  -this specificationoid contains no attribute with the given header
	 *  -the first attribute of this specificationoid with the given header contains no or several attributes
	 */
	public final String getRefOneAttributeOfFirstAttributeToString(String header)  {
		return getRefOneAttributeOfFirstAttribute(header).toString();
	}
	
	//method
	/**
	 * @return the attributes of this specificationoid
	 */
	@SuppressWarnings("unchecked")
	public final AccessorContainer<StandardSpecification> getRefAttributes() {
		return new AccessorContainer<StandardSpecification>(attributes);
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws Exception if this specificationoid contains no attribute with the given header
	 */
	public final AccessorContainer<StandardSpecification> getRefAttributesOfFirstAttribute(String header) {
		return attributes.getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}
	
	//method
	/**
	 * @return true if this specificationoid has a header
	 */
	public final boolean hasHeader() {
		return (header != null);
	}
	
	//method
	/**
	 * Sets the value of this specificationoid.
	 * 
	 * @param value
	 * @throws Exception if the given value is not valid
	 */
	public void setValue(String value) {
		
		//Extracts header and start index of attributes.
		boolean hasAttributes = false;
		int startIndex = 0;
		for (int i = 0; i < value.length(); i++) {
			Character character = value.charAt(i);
			
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
			
			//Checks whether the given specificationoid string has not as many opening brackets as closing brackets.
			if (level != 0) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(value)
				);
			}
			
			//Checks whether the last character of the given specificationoid string is a closing bracket.
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
	
	//method
	/**
	 * Sets the attributes of this specificationoid.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are null
	 */
	public final void setAttributes(Iterable<StandardSpecification> attributes) {
		
		Validator.throwExceptionIfValueIsNull("attributes", attributes);
		
		this.attributes = new List<StandardSpecification>(attributes);
	}
	
	//method
	/**
	 * Sets the header of this specificationoid.
	 * 
	 * @param header
	 * @throws Exception if the given header is null or an empty string
	 */
	public final void setHeader(String header) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty("header", header);
		
		this.header =
		header.replace(DOT_CODE, ".")
		.replace(OPEN_BRACKET_CODE, "(")
		.replace(CLOSED_BRACKET_CODE, ")");
	}
	
	//method
	/**
	 * @return the boolean this specificationoid represents
	 * @throws Exception if this specificationoid does not represent a boolean
	 */
	public final boolean toBoolean() {
		return StringHelper.toBoolean(toString());
	}
	
	//method
	public final double toDouble() {
		return StringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * @return the integer this specificationoid represents
	 * @throws Exception if this specificationoid does not represent an integer 
	 */
	public final int toInteger() {
		return StringHelper.toInt(toString());		
	}
	
	//method
	/**
	 * @return a string representation of this specificationoid
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
	
	
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		
		//Handles header if this specification has a header.
		if (hasHeader()) {
			string += getHeader();
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
				string += a.toString();
			}
			string += CharacterManager.CLOSING_BRACKET;
		}
		return string;
	}
	
	//method
	/**
	 * @return a string representation of this object in quotes
	 */
	public final String toStringInQuotes() {
		return ("'" + toString() + "'");
	}
	
	//method
	/**
	 * @param tabulators
	 * @return a formated string representation of this object with leading tabulators
	 */
	private final String toFormatedReproducingString(int tabulators) {		
		if (containsAttributes()) {
			if (allAttributesHaveNoAttributes()) {
				return (StringHelper.createTabulators(tabulators) + getHeader() + CharacterManager.OPENING_BRACKET + getRefAttributes().toString() + CharacterManager.CLOSING_BRACKET);
			}
			else {
				String formatedString = StringHelper.createTabulators(tabulators) + getHeader() + CharacterManager.OPENING_BRACKET + CharacterManager.NEW_LINE;
				int currentParameter = 1;
				for (StandardSpecification attribute: getRefAttributes()) {
					formatedString += attribute.toFormatedReproducingString(tabulators + 1);
					if (currentParameter != getRefAttributes().getElementCount()) {
						formatedString += CharacterManager.COMMA;
					}
					formatedString += CharacterManager.NEW_LINE;
					currentParameter++;
				}
				return formatedString += StringHelper.createTabulators(tabulators) + CharacterManager.CLOSING_BRACKET;
			}
		}
		else {
			return StringHelper.createTabulators(tabulators) + getHeader();		
		}
	}

	@Override
	public void addAttribute(Specification attribute) {
		// TODO Auto-generated method stub
		
	}
}
