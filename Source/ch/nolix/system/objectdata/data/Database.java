//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;

//class
public final class Database extends DatabaseObject implements IDatabase<Database, Table, Entity, Property> {
	
	//method	
	@Override
	public Table getRefTableByName(final String name) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {
		//TODO: Implement.
	}
}
