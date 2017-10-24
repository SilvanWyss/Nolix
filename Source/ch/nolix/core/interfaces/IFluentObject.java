//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A fluent object is an object that
 * can return itself casted to its concrete type.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 20
 * @param <FO> The type of a fluent object.
 */
public interface IFluentObject<FO> {

	//default method
	/**
	 * @return this object
	 */
	@SuppressWarnings("unchecked")
	public default FO getInstance() {
		return (FO)this;
	}
}
