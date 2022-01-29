//package declaration
package ch.nolix.system.sqlrawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.MultiValueQueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.MultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.RecordQueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.RecordStatementCreator;
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
			new RecordQueryCreator(),
			new RecordStatementCreator(),
			new MultiValueQueryCreator(),
			new MultiValueStatementCreator(),
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pMSSQLConnection)
		);
	}
}
