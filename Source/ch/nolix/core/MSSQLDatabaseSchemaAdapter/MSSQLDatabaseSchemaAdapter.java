//package declaration
package ch.nolix.core.MSSQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.MSSQLConnection;
import ch.nolix.core.SQLDatabaseSchemaAdapter.SQLDatabaseSchemaAdapter;

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
