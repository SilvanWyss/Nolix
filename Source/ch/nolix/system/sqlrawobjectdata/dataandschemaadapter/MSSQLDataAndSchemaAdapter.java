//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.SQLSyntaxProvider;
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
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pMSSQLConnection),
			new SQLSyntaxProvider()
		);
	}
}
