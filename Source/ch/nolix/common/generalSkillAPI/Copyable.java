//package declaration
package ch.nolix.common.generalSkillAPI;

//interface
/**
* A {@link Copyable} can create a copy of itself.
* 
* @author Silvan Wyss
* @date 2020-11-07
* @lines 10
*/
public interface Copyable<C extends Copyable<C>> {
	
	//method declaration
	/**
	 * @return a new copy of the current {@link Copyable}.
	 */
	C getCopy();
}
