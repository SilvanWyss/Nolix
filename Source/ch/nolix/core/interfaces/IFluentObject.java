//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A {@link IFluentObject} can return itself casted to its concrete type.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 20
 * @param <FO> The type of a {@link IFluentObject}.
 */
public interface IFluentObject<FO> {

	//default method
	/**
	 * @return the current {@link IFluentObject}.
	 */
	@SuppressWarnings("unchecked")
	public default FO getInstance() {
		return (FO)this;
	}
}
