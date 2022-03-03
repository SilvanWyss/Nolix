//package declaration
package ch.nolix.system.sqlrawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.rawdata.dataandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataadapter.MSSQLDataAdapter;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//constructor
	public MSSQLDataAndSchemaAdapter(final String databaseName, final MSSQLConnection pMSSQLConnection) {
		super(
			new MSSQLDataAdapter(databaseName, pMSSQLConnection),
			new MSSQLSchemaAdapter(databaseName, pMSSQLConnection)
		);
	}
}
