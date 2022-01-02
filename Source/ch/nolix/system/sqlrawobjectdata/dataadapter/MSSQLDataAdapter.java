//package declaration
package ch.nolix.system.sqlrawobjectdata.dataadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.QueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.RecordStatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//constructor
	public MSSQLDataAdapter(final MSSQLConnection pMSSQLConnection) {
		super(
			pMSSQLConnection,
			new MSSQLSchemaAdapter(pMSSQLConnection),
			new QueryCreator(),
			new RecordStatementCreator()
		);
	}
}
