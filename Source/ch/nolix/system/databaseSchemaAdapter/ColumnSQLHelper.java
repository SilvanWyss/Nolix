//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.SQL.SQLDatabaseEngine;
import ch.nolix.common.validator.Validator;

//abstract class
public abstract class ColumnSQLHelper {
	
	//package-visible attribute
	final Column column;
	
	//package-visible constructor
	ColumnSQLHelper(final Column column) {
		
		Validator.suppose(column).isOfType(Column.class);
		
		this.column = column;
	}
	
	//method declaration
	public abstract SQLDatabaseEngine getDatabaseEngine();
	
	//method
	public final String getSQLDataType() {
		//TODO: Implement column.getValueType().getSQLHelper(DatabaseEngine).getSQLDataType().
		return null;
	}
}
