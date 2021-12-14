//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataAdapter;

//class
public final class Database extends ImmutableDatabaseObject implements IDatabase<DataImplementation> {
	
	//method	
	@Override
	public ITable<DataImplementation, IEntity<DataImplementation>> getRefTableByName(final String name) {
		//TODO: Implement.
		return null;
	}
	
	//method
	IDataAdapter internalGetRefDataAdapter() {
		//TODO: Implement.
		return null;
	}
}
