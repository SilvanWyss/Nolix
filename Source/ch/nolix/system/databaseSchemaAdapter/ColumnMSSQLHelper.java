//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.SQL.SQLDatabaseEngine;

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
