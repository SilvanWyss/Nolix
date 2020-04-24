//package declaration
package ch.nolix.common.generalSkillAPI;

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
	public default LinkedList<L> intoList() {
		return new LinkedList<>((L)this);
	}
	
	//method
	/**
	 * @param type
	 * @return a new {@link LinkedList} with the current {@link Listable} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link ISmartObject} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <T> LinkedList<T> intoListAs(final Class<T> type) {
		return new LinkedList<>((T)this);
	}
}
