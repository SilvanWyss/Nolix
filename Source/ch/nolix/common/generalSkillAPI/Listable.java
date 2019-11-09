//package declaration
package ch.nolix.common.generalSkillAPI;

//own import
import ch.nolix.common.containers.List;

//interface
/**
 * A {@link Listable} can return a {@link List} that contains the {@link Listable}.
 * 
 * @author Silvan Wyss
 * @month 2018-10
 * @lines 30
 * @param <L> The type of a {@link Listable}.
 */
public interface Listable<L extends Listable<L>> {
	
	//default method
	/**
	 * @return a new {@link List} with the current {@link Listable}.
	 */
	@SuppressWarnings("unchecked")
	public default List<L> intoList() {
		return new List<>((L)this);
	}
	
	//default method
	/**
	 * @param type
	 * @return a new {@link List} with the current {@link Listable} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link ISmartObject} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <T> List<T> intoListAs(final Class<T> type) {
		return new List<>((T)this);
	}
}
