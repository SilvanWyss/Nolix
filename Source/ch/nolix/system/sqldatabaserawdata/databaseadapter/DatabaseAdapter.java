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
	
	//constant
	private static final DatabaseInspector DATABASE_INSPECTOR = new DatabaseInspector();
		
	//constructor
	protected DatabaseAdapter(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool,
		final ISchemaAdapter schemaAdapter,
		final ISqlSyntaxProvider sqlSyntaxProvider
	) {
		
		this(
			databaseName,
			sqlConnectionPool,
			DATABASE_INSPECTOR.createTableDefinitionsFrom(schemaAdapter),
			sqlSyntaxProvider
		);
		
		schemaAdapter.close();
	}
	
	//constructor
	private DatabaseAdapter(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISqlSyntaxProvider sqlSyntaxProvider
	) {
		super(
			DatabaseReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
				databaseName,
				sqlConnectionPool,
				tableInfos,
				sqlSyntaxProvider
			),
			DatabaseWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
				databaseName,
				sqlConnectionPool,
				tableInfos,
				sqlSyntaxProvider
			)
		);
	}
}
