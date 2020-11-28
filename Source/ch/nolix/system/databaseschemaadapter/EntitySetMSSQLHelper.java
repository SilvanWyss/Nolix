//package declaration
package ch.nolix.system.databaseschemaadapter;

//own imports
import ch.nolix.common.SQL.SQLDatabaseEngine;

//class
public final class EntitySetMSSQLHelper extends EntitySetSQLHelper {
	
	//constructor
	EntitySetMSSQLHelper(final EntitySet entitySet) {
		super(entitySet);
	}
	
	//method
	@Override
	public SQLDatabaseEngine getDatabaseEngine() {
		return SQLDatabaseEngine.MSSQL;
	}
}
