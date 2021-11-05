//package declaration
package ch.nolix.system.sqlintermediatedata.dataadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlintermediatedata.mssql.QueryCreator;
import ch.nolix.system.sqlintermediatedata.mssql.StatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//constructor
	public MSSQLDataAdapter(final MSSQLConnection pMSSQLConnection) {
		super(
			pMSSQLConnection,
			new MSSQLSchemaAdapter(pMSSQLConnection),
			new QueryCreator(),
			new StatementCreator()
		);
	}
}
