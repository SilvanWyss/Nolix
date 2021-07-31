//package declaration
package ch.nolix.system.sqlschema.mssqlschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaQueryCreator;
import ch.nolix.system.sqlschema.schemaadapter.SchemaReader;

//class
public final class MSSQLSchemaReader extends SchemaReader {
	
	//constructor
	public MSSQLSchemaReader(final MSSQLConnection pMSSQLConnection) {
		super(pMSSQLConnection, new MSSQLSchemaQueryCreator());
	}
}
