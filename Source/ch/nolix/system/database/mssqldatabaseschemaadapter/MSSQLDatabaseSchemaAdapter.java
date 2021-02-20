//package declaration
package ch.nolix.system.database.mssqldatabaseschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.database.sqldatabaseschemaadapter.SQLDatabaseSchemaAdapter;

//class
public final class MSSQLDatabaseSchemaAdapter
extends SQLDatabaseSchemaAdapter<MSSQLDatabaseSchemaAdapter> {
	
	//constructor
	public MSSQLDatabaseSchemaAdapter(
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		super(new MSSQLConnection(port, databaseName, userName, userPassword));
	}
	
	//constructor
	public MSSQLDatabaseSchemaAdapter(
		final String ip,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		super(new MSSQLConnection(ip, port, databaseName, userName, userPassword));
	}
}
