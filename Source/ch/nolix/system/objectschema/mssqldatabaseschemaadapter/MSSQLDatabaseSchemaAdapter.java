//package declaration
package ch.nolix.system.objectschema.mssqldatabaseschemaadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.objectschema.sqldatabaseschemaadapter.SQLDatabaseSchemaAdapter;

//class
public final class MSSQLDatabaseSchemaAdapter
extends SQLDatabaseSchemaAdapter<MSSQLDatabaseSchemaAdapter> {
	
	//constructor
	public MSSQLDatabaseSchemaAdapter(
		final int port,
		final String userName,
		final String userPassword
	) {
		super(new MSSQLConnection(port, userName, userPassword));
	}
	
	//constructor
	public MSSQLDatabaseSchemaAdapter(
		final String ip,
		final int port,
		final String userName,
		final String userPassword
	) {
		super(new MSSQLConnection(ip, port, userName, userPassword));
	}
}
