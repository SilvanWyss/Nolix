//package declaration
package ch.nolix.system.sqlschema.mssqlschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaStatementCreator;
import ch.nolix.system.sqlschema.schemaadapter.SchemaWriter;

//class
public final class MSSQLSchemaWriter extends SchemaWriter {
	
	//constructor
	public MSSQLSchemaWriter(final MSSQLConnection pMSSQLConnection) {
		super(pMSSQLConnection, new MSSQLSchemaStatementCreator());
	}
}
