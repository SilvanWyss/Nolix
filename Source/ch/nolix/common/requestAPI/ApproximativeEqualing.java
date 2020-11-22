//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
 * A {@link ApproximativeEqualing} can be asked if it equals approximatively another object.
 * 
 * @author Silvan Wyss
 * @date 2016-08-01
 * @lines 30
 */
public interface ApproximativeEqualing {
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link ApproximativeEqualing}
	 * equals the given object with a deviation,
	 * that is smaller than the default max deviation of the current {@link ApproximativeEqualing}.
	 */
	public default boolean equalsApproximatively(final Object object) {
		return equalsApproximatively(object, getDefaultMaxDeviation());
	}
	
	//method declaration
	/**
	 * @param object
	 * @param maxDeviation
	 * @return true if the current {@link ApproximativeEqualing}
	 * equals the given object with a deviation, that is smaller than the given max deviation.
	 */
	public abstract boolean equalsApproximatively(Object object, double maxDeviation);
	
	//method
	/**
	 * @return the max deviation of the current {@link ApproximativeEqualing}.
	 */
	public abstract double getDefaultMaxDeviation();
}
