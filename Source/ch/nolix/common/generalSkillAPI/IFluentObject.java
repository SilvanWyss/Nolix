//package declaration
package ch.nolix.common.generalSkillAPI;

//interface
/**
 * A {@link IFluentObject} can return itself casted into its concrete type.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 20
 * @param <FO> The type of a {@link IFluentObject}.
 */
public interface IFluentObject<FO extends IFluentObject<FO>> {
	
	//default method
	/**
	 * @return the current {@link IFluentObject} as its concrete type.
	 */
	@SuppressWarnings("unchecked")
	public default FO asConcreteType() {
		return (FO)this;
	}
}
