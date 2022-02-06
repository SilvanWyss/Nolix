//package declaration
package ch.nolix.system.sqlrawobjectdata.dataadapter;

//own imports
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.sqlrawobjectdata.mssql.MultiReferenceQueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.MultiValueQueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.MultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.RecordQueryCreator;
import ch.nolix.system.sqlrawobjectdata.mssql.RecordStatementCreator;
import ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//constructor
	public MSSQLDataAdapter(final MSSQLConnection pMSSQLConnection) {
		super(
			pMSSQLConnection,
			new MSSQLSchemaAdapter(pMSSQLConnection),
			new RecordQueryCreator(),
			new RecordStatementCreator(),
			new MultiValueQueryCreator(),
			new MultiValueStatementCreator(),
			new MultiReferenceQueryCreator()
		);
	}
}
