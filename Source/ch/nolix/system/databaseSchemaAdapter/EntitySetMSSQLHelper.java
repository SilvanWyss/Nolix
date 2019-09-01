//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.SQL.SQLDatabaseEngine;

//class
public final class EntitySetMSSQLHelper extends EntitySetSQLHelper {
	
	//package-visible constructor
	EntitySetMSSQLHelper(final EntitySet entitySet) {
		super(entitySet);
	}
	
	//method
	@Override
	public SQLDatabaseEngine getDatabaseEngine() {
		return SQLDatabaseEngine.MSSQL;
	}
}
