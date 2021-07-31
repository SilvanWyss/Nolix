//package declaration
package ch.nolix.system.sqlschema.mssqlschemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaStatementCreator;
import ch.nolix.system.sqlschema.schemaadapter.SchemaWriter;

//class
public final class MSSQLSchemaWriter extends SchemaWriter {
	
	//constructor
	public MSSQLSchemaWriter(SQLConnection pSQLConnection) {
		super(pSQLConnection, new MSSQLSchemaStatementCreator());
	}
}
