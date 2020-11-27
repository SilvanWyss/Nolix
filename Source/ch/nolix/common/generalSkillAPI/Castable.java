//package declaration
package ch.nolix.common.generalSkillAPI;

//interface
/**
 * A {@link Castable} can return itself casted into a given type.
 * 
 * @author Silvan Wyss
 * @month 2018-10
 * @lines 20
 */
public interface Castable {
	
	//method
	/**
	 * @return the current {@link Castable} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link Castable} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	default <T> T as(final Class<T> type) {
		return (T)this;
	}
}
