//package declaration
package ch.nolix.common.generalSkillAPI;

//own import
import ch.nolix.common.container.LinkedList;

//interface
/**
 * A {@link Listable} can return a {@link LinkedList} that contains the {@link Listable}.
 * 
 * @author Silvan Wyss
 * @month 2018-10
 * @lines 30
 * @param <L> The type of a {@link Listable}.
 */
public interface Listable<L extends Listable<L>> {
	
	//method
	/**
	 * @return a new {@link LinkedList} with the current {@link Listable}.
	 */
	@SuppressWarnings("unchecked")
	default LinkedList<L> intoList() {
		return LinkedList.withElements((L)this);
	}
	
	//method
	/**
	 * @param type
	 * @return a new {@link LinkedList} with the current {@link Listable} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link ISmartObject} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	default <T> LinkedList<T> intoListAs(final Class<T> type) {
		return LinkedList.withElements((T)this);
	}
}
