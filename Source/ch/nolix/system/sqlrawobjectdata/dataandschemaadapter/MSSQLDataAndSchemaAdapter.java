//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.QueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.StatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAndSchemaAdapter extends DataAndSchemaAdapter {
	
	//static method
	public static MSSQLDataAndSchemaAdapter withDatabaseConnection(final MSSQLConnection pMSSQLConnection) {
		return new MSSQLDataAndSchemaAdapter(pMSSQLConnection);
	}
	
	//constructor
	private MSSQLDataAndSchemaAdapter(final MSSQLConnection pMSSQLConnection) {
		super(
			pMSSQLConnection,
			new MSSQLSchemaAdapter(pMSSQLConnection),
			new QueryCreator(),
			new StatementCreator(),
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pMSSQLConnection)
		);
	}
}
