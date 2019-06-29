//package declaration
package ch.nolix.core.generalSkillAPI;

//own import
import ch.nolix.core.container.List;

//interface
/**
 * A {@link ISmartObject} provides general methods on itself
 * that are independent from its specific purpose.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 30
 * @param <SO> The type of a {@link ISmartObject}.
 */
public interface ISmartObject<SO extends ISmartObject<SO>>
extends
	Castable,
	IFluentObject<SO>,
	Listable<SO>,
	TypeRequestable {
	
	//default method
	/**
	 * @param type
	 * @return a new {@link List} with the current {@link ISmartObject} as instance of the given type.
	 * @param type
	 * @throws ClassCastException if the current {@link ISmartObject} is not of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <T> List<T> intoListAs(final Class<T> type) {
		return new List<>((T)this);
	}
}
