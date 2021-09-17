//package declaration
package ch.nolix.system.sqlschema.mssqlschemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaQueryCreator;
import ch.nolix.system.sqlschema.mssqllanguage.MSSQLSchemaStatementCreator;
import ch.nolix.system.sqlschema.schemaadapter.SchemaAdapter;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constructor
	public MSSQLSchemaAdapter(final SQLConnection pSQLConnection) {
		super(pSQLConnection, new MSSQLSchemaQueryCreator(), new MSSQLSchemaStatementCreator());
	}
}
