//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdatabase.databaseadapter.BaseDatabaseAdapter;
import ch.nolix.system.sqldatabaserawdata.databaseinspector.DatabaseInspector;
import ch.nolix.system.sqldatabaserawdata.databasereader.DatabaseReader;
import ch.nolix.system.sqldatabaserawdata.databasewriter.DatabaseWriter;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DatabaseAdapter extends BaseDatabaseAdapter {
	
	//static attribute
	private static final DatabaseInspector databaseInspector = new DatabaseInspector();
		
	//constructor
	protected DatabaseAdapter(
		final String databaseName,
		final SqlConnectionPool pSQLConnectionPool,
		final ISchemaAdapter schemaAdapter,
		final ISqlSyntaxProvider pSQLSyntaxProvider
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
		final SqlConnectionPool pSQLConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISqlSyntaxProvider pSQLSyntaxProvider
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
