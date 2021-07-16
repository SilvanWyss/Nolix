//package
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

//own imports
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;

//interface
public interface IDatabaseObject extends ICloseableElement {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithActualDatabase();
}
