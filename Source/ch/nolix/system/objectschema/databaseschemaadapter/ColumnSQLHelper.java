//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public abstract class ColumnSQLHelper {
	
	//attribute
	private final Column column;
	
	//constructor
	ColumnSQLHelper(final Column column) {
		
		Validator.assertThat(column).isOfType(Column.class);
		
		this.column = column;
	}
	
	//method declaration
	public abstract SQLDatabaseEngine getDatabaseEngine();
	
	//method
	public final String getSQLDataType() {
		//TODO: column.getValueType().getSQLHelper(DatabaseEngine).getSQLDataType()
		return null;
	}
	
	//method
	protected final Column getRefColumn() {
		return column;
	}
}
