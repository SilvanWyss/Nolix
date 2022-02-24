//package declaration
package ch.nolix.system.sqlrawobjectdata.dataadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.SQLSyntaxProvider;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//constructor
	public MSSQLDataAdapter(final String databaseName, final MSSQLConnection pMSSQLConnection) {
		super(
			pMSSQLConnection,
			new MSSQLSchemaAdapter(databaseName, pMSSQLConnection),
			new SQLSyntaxProvider()
		);
	}
}
