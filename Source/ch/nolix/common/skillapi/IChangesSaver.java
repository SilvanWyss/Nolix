//package declaration
package ch.nolix.common.skillapi;

//interface
/**
 * A {@link IChangesSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 20
 * @param <CS> The type of a {@link IChangeSaver}.
 */
public interface IChangesSaver<CS extends IChangesSaver<CS>> extends Resettable<CS> {
	
	//method declaration
	/**
	 * @return true if the current {@link IChangesSaver} has changes.
	 */
	boolean hasChanges();
	
	//method declaration
	/**
	 * Saves the changes of the current {@link IChangesSaver}.
	 */
	void saveChanges();
}
