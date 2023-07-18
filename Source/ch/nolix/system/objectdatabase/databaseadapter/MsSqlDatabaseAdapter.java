//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

//own imports
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.core.sql.SqlDatabaseEngine;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;
import ch.nolix.system.objectschema.schemaadapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.dataandschemaadapter.MsSqlDatabaseAndSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MsSqlDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MsSqlDatabaseAdapterBuilder toIpOrAddress(final String ipOrDomain) {
		return new MsSqlDatabaseAdapterBuilder(ipOrDomain);
	}
	
	//static method
	public static MsSqlDatabaseAdapterBuilder toLocalHost() {
		return new MsSqlDatabaseAdapterBuilder(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//attribute
	private final SqlConnectionPool sqlConnectionPool; 
	
	//constructor
	MsSqlDatabaseAdapter(
		final String ipOrDomain,
		final int port,
		final String databaseName,
		final String loginName,
		final String loginPassword,
		final ISchema schema
	) {
		this(
			databaseName,
			schema,
			SqlConnectionPool
			.forIpOrDomain(ipOrDomain)
			.andPort(port)
			.andDatabase(databaseName)
			.withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
			.usingLoginName(loginName)
			.andLoginPassword(loginPassword)
		);
	}
	
	//constructor
	private MsSqlDatabaseAdapter(
		final String databaseName,
		final ISchema schema,
		final SqlConnectionPool sqlConnectionPool
	) {
		
		super(
			databaseName,
			MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
			schema,
			() ->
			MsSqlDatabaseAndSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				sqlConnectionPool
			)
		);
		
		this.sqlConnectionPool = sqlConnectionPool;
	}
	
	//method
	@Override
	public DatabaseAdapter getEmptyCopy() {
		return new MsSqlDatabaseAdapter(getDatabaseName(), getSchema(), sqlConnectionPool);
	}
}
