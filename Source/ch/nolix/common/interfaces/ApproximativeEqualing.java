//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * An approximative equaling object can be checked if it equals approximatively an other object.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 30
 */
public interface ApproximativeEqualing {
	
	//constant
	public final static double DEFAULT_DEVIATION = 0.000000001; //10^-9
	
	//default method
	/**
	 * @param object
	 * @return true if this object equals the given object with a deviation that is smaller than the default deviation.
	 */
	public default boolean equalsApproximatively(final Object object) {
		return equalsApproximatively(object, DEFAULT_DEVIATION);
	}
	
	//abstract method
	/**
	 * @param object
	 * @param maxDeviation
	 * @return true if this object equals the given object with a deviation that is smaller than the given max deviation.
	 */
	public abstract boolean equalsApproximatively(Object object, double maxDeviation);
}
