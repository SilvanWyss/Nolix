//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.helper.StringHelper;

//abstract class
/**
 * A specification can have:
 * -1 header
 * -an arbitrary number of attributes that are specifications themselves
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 300
 */
public abstract class Specification {

	//abstract method
	/**
	 * Adds the given attribute to this specification.
	 * 
	 * @param attribute
	 */
	public abstract void addAttribute(final Specification attribute);
	
	//method
	/**
	 * Adds the given attributes to this specification.
	 * 
	 * @param attributes
	 */
	public final void addAttribute(final Specification... attributes) {
		
		//Iterates the given attributes.
		for (final Specification a : attributes) {
			addAttribute(a);
		}
	}
	
	//method
	/**
	 * Adds the given attributes to this specification.
	 * 
	 * @param attributes
	 */
	public final void addAttributes(final Iterable<Specification> attributes) {
		
		//Iterates the given attributes.
		attributes.forEach(a -> addAttribute(a));
	}
	
	//method
	/**
	 * @return true if all attributes of this standard specification have no attributes
	 */
	public boolean allAttributesHaveNoAttributes() {
		return getRefAttributes().containsNone(s -> s.containsAttributes());
	}
	
	//abstract method
	/**
	 * @return true if this specification contains attributes.
	 */
	public abstract boolean containsAttributes();
	
	//method
	/**
	 * @return the number of attributes of this specification.
	 */
	public final int getAttributeCount() {
		return getRefAttributes().getElementCount();
	}
	
	//method
	/**
	 * @return the string representations of the attributes of this specification.
	 */
	public final List<String> getAttributesToStrings() {
		return getRefAttributes().to(a -> a.toString());
	}
	
	//abstract method
	/**
	 * @return the header of this specification.
	 */
	public abstract String getHeader();
	
	//method
	/**
	 * @return the header of this specification in quotes.
	 */
	public final String getHeaderInQuotes() {
		return (
			CharacterCatalogue.APOSTROPH
			+ getHeader()
			+ CharacterCatalogue.APOSTROPH
		);
	}
	
	//abstract method
	/**
	 * @return the attributes of this specification.
	 */
	public abstract <S extends Specification> AccessorContainer<S> getRefAttributes();
	
	//abstract method
	/**
	 * @return the one attribute of this specification.
	 */
	public abstract <S extends Specification> S getRefOneAttribute();
	
	//abstract method
	/**
	 * @return true if this specification has a header.
	 */
	public abstract boolean hasHeader();
	
	//method
	/**
	 * @param header
	 * @return true if this specification has the given header.
	 */
	public final boolean hasHeader(final String header) {
		
		//Handles the case if this specification has no header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case if this specification has a header.
		return getHeader().equals(header);
	}
	
	//method
	/**
	 * Saves this specification to the file with the given path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if the given file path is not valid.
	 */
	public final void saveToFile(final String filePath) {
		new FileSystemAccessor().createFile(
			filePath,
			toFormatedReproducingString()
		);
	}
	
	//abstract method
	/**
	 * Sets the header of this specification.
	 * 
	 * @param header
	 */
	public abstract void setHeader(final String header);
	
	//method
	/**
	 * @return the boolean this specification represents.
	 * @throws InvalidArgumenException if this specification represents no boolean.
	 */
	public final boolean toBoolean() {
		return StringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * @return the double this specification represents.
	 * @throws InvalidArgumentException if this specification represents no double.
	 */
	public final double toDouble() {
		return StringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * @return a formated reproducing string representation of this specification.
	 */
	public String toFormatedReproducingString() {
		return toFormatedReproducingString(0);
	}
	
	//method
	/**
	 * @return the integer this specification represents.
	 * @throws InvalidArgumentException if this specification represents no int.
	 */
	public final int toInt() {
		return StringHelper.toInt(toString());		
	}
	
	//method
	/**
	 * @return a new standard specification representing this specification.
	 */
	public final StandardSpecification toStandardSpecification() {
		
		final StandardSpecification standardSpecification = new StandardSpecification();
		
		if (hasHeader()) {
			standardSpecification.setHeader(getHeader());
		}
		
		getRefAttributes().forEach(a -> a.addAttribute(a));
		
		return standardSpecification;
	}
	
	//method
	/**
	 * @return a string representation of this specification.
	 */
	public final String toString() {
		
		String string = StringCatalogue.EMPTY_STRING;
		
		//Handles the header if this specification has a header.
		if (hasHeader()) {
			string += getHeader();
		}
		
		//Handles the attributes if this specification contains attributes.
		if (containsAttributes()) {
			
			final IContainer<Specification> attributes = getRefAttributes();
			
			string += CharacterCatalogue.OPENING_BRACKET;
			boolean begin = true;
			for (final Specification a : attributes) {
				if (begin) {
					begin = false;
				}
				else {
					string += CharacterCatalogue.COMMA;
				}
				string += a.toString();
			}
			string += CharacterCatalogue.CLOSING_BRACKET;
		}
		
		return string;
	}
	
	//method
	/**
	 * @param leadingTabulatorCount
	 * @return a formated string representation of this specification
	 * with as many leading tabulators as the given leading tabulator count says.
	 */
	private final String toFormatedReproducingString(int leadingTabulatorCount) {
		
		//Handles the case if this specification contains no attributes.
		if (!containsAttributes()) {
			return StringHelper.createTabulators(leadingTabulatorCount) + getHeader();
		}
		
		//Handles the case if this specification contains attributes.
		else {
			
			//Handles the case if all attributes have no attributes.
			if (allAttributesHaveNoAttributes()) {
				return (
					StringHelper.createTabulators(leadingTabulatorCount)
					+ getHeader()
					+ CharacterCatalogue.OPENING_BRACKET
					+ getRefAttributes().toString()
					+ CharacterCatalogue.CLOSING_BRACKET
				);
			}
			
			//Handles the case if an attribute have attributes.
			else {
				
				String formatedString =
				StringHelper.createTabulators(leadingTabulatorCount)
				+ getHeader()
				+ CharacterCatalogue.OPENING_BRACKET
				+ CharacterCatalogue.NEW_LINE;
				
				//Iterates the attributes of this specification.
				int currentAttributeIndex = 1;
				for (final Specification attribute: getRefAttributes()) {
					
					formatedString += attribute.toFormatedReproducingString(leadingTabulatorCount + 1);
					
					if (currentAttributeIndex != getRefAttributes().getElementCount()) {
						formatedString += CharacterCatalogue.COMMA;
					}
					
					formatedString += CharacterCatalogue.NEW_LINE;
					
					currentAttributeIndex++;
				}
				
				return (
					formatedString
					+ StringHelper.createTabulators(leadingTabulatorCount)
					+ CharacterCatalogue.CLOSING_BRACKET
				);
			}
		}
	}
}
