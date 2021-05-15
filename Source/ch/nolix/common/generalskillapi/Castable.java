//package declaration
package ch.nolix.common.generalskillapi;

//interface
/**
 * A {@link Castable} can return itself casted into a given type.
 * 
 * @author Silvan Wyss
 * @date 2018-10-20
 * @lines 20
 */
public interface Castable {
	
	//method
	/**
	 * @return the current {@link Castable} as instance of the given type.
	 * @param type
	 * @param <T> is the given type.
	 * @throws ClassCastException if the current {@link Castable} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	default <T> T as(final Class<T> type) {
		return (T)this;
	}
}
