//package declaration
package ch.nolix.system.MSSQLDatabaseSchemaAdapter;

import ch.nolix.common.SQL.MSSQLConnection;
import ch.nolix.system.SQLDatabaseSchemaAdapter.SQLDatabaseSchemaAdapter;

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
