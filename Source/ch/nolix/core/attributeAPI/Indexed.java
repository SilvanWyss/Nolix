//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Indexed} has an index.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public interface Indexed {
	
	//abstract method
	/**
	 * @return the index of the current {@link Indexed}.
	 */
	public abstract int getIndex();
	
	//default method
	/**
	 * @return the index of the current {@link Indexed} as {@link String}.
	 */
	public default String getIndexAsString() {
		return String.valueOf(getIndex());
	}
	
	//default method
	/**
	 * @return the index of the current {@link Indexed} as {@link String} in quotes.
	 */
	public default String getIndexAsStringInQuotes() {
		return ("'" + getIndexAsString() + "'");
	}
	
	//default method
	/**
	 * @param index
	 * @return true if the current {@link Indexed} has the given index.
	 */
	public default boolean hasIndex(final int index) {
		return (getIndex() == index);
	}
	
	//default method
	/**
	 * @param object
	 * @return true if the current {@link Indexed} has the same index as the given object.
	 */
	public default boolean hasSameIndexAs(final Indexed object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return hasIndex(object.getIndex());
	}
}
