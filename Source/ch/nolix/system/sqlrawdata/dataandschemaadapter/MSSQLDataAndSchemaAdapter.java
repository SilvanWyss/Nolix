//package declaration
package ch.nolix.system.sqlrawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.sqlrawdata.mssql.SQLSyntaxProvider;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAndSchemaAdapter extends DataAndSchemaAdapter {
	
	//static method
	public static MSSQLDataAndSchemaAdapter withDatabaseNameAndDatabaseConnection(
		final String databaseName,
		final MSSQLConnection pMSSQLConnection
	) {
		return new MSSQLDataAndSchemaAdapter(databaseName, pMSSQLConnection);
	}
	
	//constructor
	private MSSQLDataAndSchemaAdapter(final String databaseName, final MSSQLConnection pMSSQLConnection) {
		super(
			databaseName,
			pMSSQLConnection,
			new MSSQLSchemaAdapter(databaseName, pMSSQLConnection),
			new ch.nolix.system.sqlbasicschema.schemaadapter.MSSQLSchemaAdapter(pMSSQLConnection),
			new SQLSyntaxProvider()
		);
	}
}
