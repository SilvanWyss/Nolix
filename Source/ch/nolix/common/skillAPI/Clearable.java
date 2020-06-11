//package declaration
package ch.nolix.common.skillAPI;

//own import
import ch.nolix.common.requestAPI.EmptinessRequestable;

//interface
/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 20
 * @param <C> The type of a {@link Clearable}.
 */
public interface Clearable<C extends Clearable<C>> extends EmptinessRequestable {
	
	//method declaration
	/**
	 * Removes the elements of the current {@link Clearable}.
	 * 
	 * @return the current {@link Clearable}.
	 */
	public abstract C clear();
}
