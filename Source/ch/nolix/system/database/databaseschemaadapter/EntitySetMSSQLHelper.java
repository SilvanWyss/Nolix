//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.common.sql.SQLDatabaseEngine;

//class
public final class EntitySetMSSQLHelper extends EntitySetSQLHelper {
	
	//constructor
	public EntitySetMSSQLHelper(final EntitySet entitySet) {
		super(entitySet);
	}
	
	//method
	@Override
	public SQLDatabaseEngine getDatabaseEngine() {
		return SQLDatabaseEngine.MSSQL;
	}
}
