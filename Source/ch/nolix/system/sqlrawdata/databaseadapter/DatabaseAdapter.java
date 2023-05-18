//package declaration
package ch.nolix.system.sqlrawdata.databaseadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdatabase.databaseadapter.BaseDatabaseAdapter;
import ch.nolix.system.sqlrawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqlrawdata.databasereader.DatabaseReader;
import ch.nolix.system.sqlrawdata.databasewriter.DatabaseWriter;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DatabaseAdapter extends BaseDatabaseAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
		
	//constructor
	protected DatabaseAdapter(
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
	private DatabaseAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		super(
			DatabaseReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
				databaseName,
				pSQLConnectionPool,
				tableInfos,
				pSQLSyntaxProvider
			),
			DatabaseWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
				databaseName,
				pSQLConnectionPool,
				tableInfos,
				pSQLSyntaxProvider
			)
		);
	}
}
