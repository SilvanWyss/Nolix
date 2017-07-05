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
import ch.nolix.core.container.List;
import ch.nolix.core.helper.StringHelper;

//class
public final class StandardSpecification extends Specificationoid {
	
	//static mehtod
	public static final StandardSpecification loadSpecification(final String path) {
		final StandardSpecification standardSpecification = new StandardSpecification();
		standardSpecification.loadFromFile(path);
		return standardSpecification;
	}
	
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
	public StandardSpecification(String header, List<StandardSpecification> attributes) {
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
}
