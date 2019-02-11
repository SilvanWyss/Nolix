//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.SQLDatabaseEngine;

//class
public final class ColumnMSSQLHelper extends ColumnSQLHelper {
	
	//package-visible constructor
	ColumnMSSQLHelper(final Column column) {
		super(column);
	}
	
	//method
	@Override
	public SQLDatabaseEngine getDatabaseEngine() {
		return SQLDatabaseEngine.MSSQL;
	}
}
