//package declaration
package ch.nolix.core.generalSkillAPI;

import ch.nolix.core.containers.List;

//interface
/**
 * A {@link Listable} can return a {@link List} that contains the {@link Listable}.
 * 
 * @author Silvan Wyss
 * @month 2018-10
 * @lines 20
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
}
