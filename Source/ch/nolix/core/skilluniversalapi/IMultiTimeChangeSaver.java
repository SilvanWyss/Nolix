//package declaration
package ch.nolix.core.skilluniversalapi;

import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.requestuniversalapi.ChangeRequestable;

//interface
public interface IMultiTimeChangeSaver extends ChangeRequestable, GroupCloseable, Resettable {
	
	//method declaration
	int getSaveCount();
	
	//method declaration
	void saveChangesAndReset();
}
