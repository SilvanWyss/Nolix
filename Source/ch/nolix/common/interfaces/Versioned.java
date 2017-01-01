//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A versioned object has a single version number.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 10
 */
public interface Versioned {

	//abstract method
	/**
	 * @return the version number of this versioned object.
	 */
	public abstract int getVersion();
}
