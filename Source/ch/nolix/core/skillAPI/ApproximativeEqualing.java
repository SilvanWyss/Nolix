//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * An approximative equaling object can be checked if
 * it equals approximatively another object.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 30
 */
public interface ApproximativeEqualing {
	
	//default max deviation
	public static final double DEFAULT_MAX_DEVIATION = 0.000000001; //10^-9
	
	//default method
	/**
	 * @param object
	 * @return true if this approximative equaling object
	 * equals the given object with a deviation
	 * that is smaller than the default max deviation.
	 */
	public default boolean equalsApproximatively(final Object object) {
		return equalsApproximatively(object, DEFAULT_MAX_DEVIATION);
	}
	
	//abstract method
	/**
	 * @param object
	 * @param maxDeviation
	 * @return true if this object
	 * equals the given object with a deviation
	 * that is smaller than the given max deviation.
	 */
	public abstract boolean equalsApproximatively(Object object, double maxDeviation);
}
