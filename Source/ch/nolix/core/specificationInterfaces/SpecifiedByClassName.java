//package declaration
package ch.nolix.core.specificationInterfaces;

//interface
/**
 * A specified by class name object is a specified object
 * whose type is the simple class name.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public interface SpecifiedByClassName extends Specified {
	
	//default method
	/**
	 * @return the type of this specified by class name object.
	 */
	public default String getType() {
		return getClass().getSimpleName();
	}
}
