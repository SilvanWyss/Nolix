//package declaration
package ch.nolix.coreapi.functionapi.mutationapi;

import ch.nolix.coreapi.functionapi.requestapi.ChangeRequestable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IMultiTimeChangeSaver extends ChangeRequestable, GroupCloseable, Resettable {
	
	//method declaration
	int getSaveCount();
	
	//method declaration
	void saveChangesAndReset();
}
