//package declaration
package ch.nolix.system.sqlrawdata.dataadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
	protected DataAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ISchemaAdapter schemaAdapter,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		this(
			databaseName,
			pSQLConnectionPool,
			databaseInspector.createTableDefinitionsFrom(schemaAdapter),
			pSQLSyntaxProvider
		);
		
		schemaAdapter.close();
	}
	
	//constructor
	private DataAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		super(
			DataReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
				databaseName,
				pSQLConnectionPool,
				tableInfos,
				pSQLSyntaxProvider
			),
			DataWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
				databaseName,
				pSQLConnectionPool,
				tableInfos,
				pSQLSyntaxProvider
			)
		);
	}
}
