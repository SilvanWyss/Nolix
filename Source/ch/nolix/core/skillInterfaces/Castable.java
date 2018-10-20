//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-10
 * @lines 20
 */
public interface Castable {
	
	//default method
	/**
	 * @return the current {@link Castable} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link Castable} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <T> T as(final Class<T> type) {
		return (T)this;
	}
}
