//package declaration
package ch.nolix.core.specification;

import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.container.AccessorContainer;

//abstract class
/**
 * A specification can have:
 * -1 header
 * -an arbitrary number of attributes that are specifications themselves
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 100
 */
public abstract class Specification {

	//abstract method
	/**
	 * Adds the given attribute to this specification.
	 * 
	 * @param attribute
	 */
	public abstract void addAttribute(final Specification attribute);
	
	//default method
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
	
	//default method
	/**
	 * @return the number of attributes of this specification.
	 */
	public final int getAttributeCount() {
		return getRefAttributes().getElementCount();
	}
	
	//abstract method
	/**
	 * @return the header of this specification.
	 */
	public abstract String getHeader();
	
	//default method
	/**
	 * @return the header of this specification in quotes.
	 */
	public final String getHeaderInQuotes() {
		return (
			CharacterManager.APOSTROPH
			+ getHeader()
			+ CharacterManager.APOSTROPH
		);
	}
	
	//abstract method
	/**
	 * @return true if this specification has a header.
	 */
	public abstract boolean hasHeader();
	
	//default method
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
	
	//abstract method
	/**
	 * @return the attributes of this specification.
	 */
	public abstract <S extends Specification> AccessorContainer<S> getRefAttributes();
	
	//method
	/**
	 * Sets the header of this specification.
	 * 
	 * @param header
	 */
	public abstract void setHeader(final String header);
}
