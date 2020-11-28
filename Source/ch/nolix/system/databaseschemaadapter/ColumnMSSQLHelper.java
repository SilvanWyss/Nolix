//package declaration
package ch.nolix.system.databaseschemaadapter;

import ch.nolix.common.sql.SQLDatabaseEngine;

//class
public final class ColumnMSSQLHelper extends ColumnSQLHelper {
	
	//constructor
	ColumnMSSQLHelper(final Column column) {
		super(column);
	}
	
	//method
	@Override
	public SQLDatabaseEngine getDatabaseEngine() {
		return SQLDatabaseEngine.MSSQL;
	}
}
