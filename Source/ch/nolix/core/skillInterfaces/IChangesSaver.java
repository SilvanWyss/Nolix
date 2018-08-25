//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A {@link IChangesSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 20
 * @param <CS> The type of a {@link IChangeSaver}.
 */
public interface IChangesSaver<CS extends IChangesSaver<CS>>
extends Resettable<CS> {
	
	//abstract method
	/**
	 * @return true if the current {@link IChangesSaver} has changes.
	 */
	public abstract boolean hasChanges();

	//abstract method
	/**
	 * Saves the changes of the current {@link IChangesSaver}.
	 */
	public abstract void saveChanges();
}
