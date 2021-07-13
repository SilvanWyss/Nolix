//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;

//class
abstract class DatabaseObject implements IDatabaseObject {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
}
