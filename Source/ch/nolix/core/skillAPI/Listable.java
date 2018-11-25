//package declaration
package ch.nolix.core.skillAPI;

//own import
import ch.nolix.core.container.List;

//interface
/**
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
		return new List<L>((L)this);
	}
}
