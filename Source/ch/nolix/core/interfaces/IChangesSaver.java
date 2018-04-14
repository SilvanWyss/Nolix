//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A {@link IChangesSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 20
 */
public interface IChangesSaver extends Resettable {
	
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
