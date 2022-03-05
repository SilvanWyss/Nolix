//package declaration
package ch.nolix.system.sqlbasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlbasicschema.mssqllanguage.MSSQLSchemaQueryCreator;
import ch.nolix.system.sqlbasicschema.mssqllanguage.MSSQLSchemaStatementCreator;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter fromSQLConnectionPool(final SQLConnectionPool pSQLConnectionPool) {
		return new MSSQLSchemaAdapter(pSQLConnectionPool.borrowSQLConnection());
	}
	
	//constructor
	public MSSQLSchemaAdapter(final SQLConnection pSQLConnection) {
		super(pSQLConnection, new MSSQLSchemaQueryCreator(), new MSSQLSchemaStatementCreator());
	}
}
