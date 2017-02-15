/*
 * file:	Specification.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	170
 */

//package declaration
package ch.nolix.common.specification;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import java.nio.file.Paths;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.helper.CharacterHelper;
import ch.nolix.common.helper.StringHelper;

//class
public final class Specification extends Specificationoid {
	
	//static mehtod
	public static final Specification loadSpecification(final String path) {
		final Specification specification = new Specification();
		specification.loadFromFile(path);
		return specification;
	}
	
	//constructor
	/**
	 * Creates new specification without header and without attributes.
	 */
	public Specification() {}
		
	//constructor
	/**
	 * Creates new specification the given string represents.
	 * 
	 * @param string
	 * @throws Exception if the given string is not valid
	 */
	public Specification(String string) {
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
	public Specification(String header, List<Specification> attributes) {
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
	public Specification(String header, String... attributes) {
		
		setHeader(header);
		
		for (String a: attributes) {
			addAttribute(a);
		}
	}
	
	public final boolean equals(Object object) {

		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Specification)) {
			return false;
		}
		
		return object.toString().equals(toString());
	}
	
	//method
	/**
	 * @return a clone of this specification
	 */
	public final Specification getClone() {
		return new Specification(getHeader(), getRefAttributes().toContainer(a -> a.getClone()));
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
				return (StringHelper.createTabulators(tabulators) + getHeader() + CharacterHelper.OPENING_BRACKET + getRefAttributes().toString() + CharacterHelper.CLOSING_BRACKET);
			}
			else {
				String formatedString = StringHelper.createTabulators(tabulators) + getHeader() + CharacterHelper.OPENING_BRACKET + CharacterHelper.NEW_LINE;
				int currentParameter = 1;
				for (Specification attribute: getRefAttributes()) {
					formatedString += attribute.toFormatedReproducingString(tabulators + 1);
					if (currentParameter != getRefAttributes().getSize()) {
						formatedString += CharacterHelper.COMMA;
					}
					formatedString += CharacterHelper.NEW_LINE;
					currentParameter++;
				}
				return formatedString += StringHelper.createTabulators(tabulators) + CharacterHelper.CLOSING_BRACKET;
			}
		}
		else {
			return StringHelper.createTabulators(tabulators) + getHeader();		
		}
	}
}
