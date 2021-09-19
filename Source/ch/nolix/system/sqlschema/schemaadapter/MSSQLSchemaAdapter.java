//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaQueryCreator;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaStatementCreator;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constructor
	public MSSQLSchemaAdapter(final SQLConnection pSQLConnection) {
		super(pSQLConnection, new MSSQLSchemaQueryCreator(), new MSSQLSchemaStatementCreator());
	}
}
