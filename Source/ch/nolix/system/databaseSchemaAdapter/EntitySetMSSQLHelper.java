//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own import
import ch.nolix.core.SQL.SQLDatabaseEngine;

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
