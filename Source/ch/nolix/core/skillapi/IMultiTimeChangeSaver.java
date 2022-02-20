//package declaration
package ch.nolix.core.skillapi;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.requestapi.ChangeRequestable;

//interface
public interface IMultiTimeChangeSaver extends ChangeRequestable, GroupCloseable, Resettable {
	
	//method declaration
	int getSaveCount();
	
	//method declaration
	void saveChangesAndReset();
}
