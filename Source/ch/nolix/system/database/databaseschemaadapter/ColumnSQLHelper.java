//package declaration
package ch.nolix.system.database.databaseschemaadapter;

import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
import ch.nolix.common.sql.SQLDatabaseEngine;

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
