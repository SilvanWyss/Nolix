//package declaration
package ch.nolix.core.skilluniversalapi;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.coreapi.requestuniversalapi.ChangeRequestable;

//interface
public interface IMultiTimeChangeSaver extends ChangeRequestable, GroupCloseable, Resettable {
	
	//method declaration
	int getSaveCount();
	
	//method declaration
	void saveChangesAndReset();
}
