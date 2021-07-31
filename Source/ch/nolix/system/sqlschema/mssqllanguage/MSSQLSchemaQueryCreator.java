//package declaration
package ch.nolix.system.sqlschema.mssqllanguage;

//own imports
import ch.nolix.techapi.sqlschemaapi.schemalanguageapi.ISchemaQueryCreator;

//class
public final class MSSQLSchemaQueryCreator implements ISchemaQueryCreator {
	
	//method
	@Override
	public String createQueryToLoadNameAndDataTypeOfColumns(final String tableName) {
		return
		"SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '" + tableName + "'";
	}
	
	//method
	@Override
	public String createQueryToLoadNameOfTables() {
		return "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
	}
}
