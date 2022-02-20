//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.SQLSyntaxProvider;
import ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter;

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
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pMSSQLConnection),
			new SQLSyntaxProvider()
		);
	}
}
