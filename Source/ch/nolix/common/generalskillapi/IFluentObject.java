//package declaration
package ch.nolix.common.generalskillapi;

//interface
/**
 * A {@link IFluentObject} can return itself casted into its concrete type.
 * 
 * @author Silvan Wyss
 * @date 2017-11-01
 * @lines 20
 * @param <FO> is the type of a {@link IFluentObject}.
 */
public interface IFluentObject<FO extends IFluentObject<FO>> {
	
	//method
	/**
	 * @return the current {@link IFluentObject} as its concrete type.
	 */
	@SuppressWarnings("unchecked")
	default FO asConcrete() {
		return (FO)this;
	}
}
