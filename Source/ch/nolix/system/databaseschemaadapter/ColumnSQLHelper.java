//package declaration
package ch.nolix.system.databaseschemaadapter;

import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.common.validator.Validator;

//class
public abstract class ColumnSQLHelper {
	
	//attribute
	private final Column column;
	
	//visibility-reduced constructor
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
