//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * An indexed object has an index.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Indexed {

	//abstract method
	/**
	 * @return the index of this indexed object.
	 */
	public abstract int getIndex();
	
	//default method
	/**
	 * @param index
	 * @return true if this indexed object has the given index.
	 */
	public default boolean hasIndex(final int index) {
		return (getIndex() == index);
	}
}
