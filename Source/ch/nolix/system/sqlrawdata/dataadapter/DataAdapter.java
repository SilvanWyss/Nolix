//package declaration
package ch.nolix.system.sqlrawdata.dataadapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.rawdata.dataadapter.BaseDataAdapter;
import ch.nolix.system.sqlrawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawdata.datareader.DataReader;
import ch.nolix.system.sqlrawdata.datawriter.DataWriter;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DataAdapter extends BaseDataAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
		
	//constructor
	public DataAdapter(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		this(
			pSQLConnection,
			databaseInspector.createTableDefinitionsFrom(schemaAdapter),
			pSQLSyntaxProvider
		);
	}
	
	//constructor
	private DataAdapter(
		final SQLConnection pSQLConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		super(
			new DataReader(pSQLConnection, tableInfos, pSQLSyntaxProvider),
			new DataWriter(pSQLConnection, tableInfos, pSQLSyntaxProvider)
		);
	}
}
