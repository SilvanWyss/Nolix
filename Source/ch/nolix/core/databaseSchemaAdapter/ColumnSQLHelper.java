//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.SQLDatabaseEngine;
import ch.nolix.core.validator.Validator;

//abstract class
public abstract class ColumnSQLHelper {
	
	//package-visible attribute
	final Column column;
	
	//package-visible constructor
	ColumnSQLHelper(final Column column) {
		
		Validator.suppose(column).isOfType(Column.class);
		
		this.column = column;
	}
	
	//abstract method
	public abstract SQLDatabaseEngine getDatabaseEngine();
	
	//method
	public final String getSQLDataType() {
		//TODO: Add getValueType method to Column.
		//return column.getValueType().getSQLHelper(getDatabaseEngine()).getSQLDataType();
		return null;
	}
}
