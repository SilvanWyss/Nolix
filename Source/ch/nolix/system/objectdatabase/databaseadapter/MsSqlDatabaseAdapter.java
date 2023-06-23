//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

//own imports
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.core.sql.SqlDatabaseEngine;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;
import ch.nolix.system.objectschema.schemaadapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.dataandschemaadapter.MSSQLDatabaseAndSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MsSqlDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MsSqlDatabaseAdapterBuilder toIpOrAddress(final String ipOrAddressName) {
		return new MsSqlDatabaseAdapterBuilder(ipOrAddressName);
	}
	
	//static method
	public static MsSqlDatabaseAdapterBuilder toLocalHost() {
		return new MsSqlDatabaseAdapterBuilder(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//attribute
	private final SqlConnectionPool mSQLConnectionPool; 
	
	//constructor
	MsSqlDatabaseAdapter(
		final String ipOrAddressName,
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
			.forIpOrAddressName(ipOrAddressName)
			.andPort(port)
			.andDatabase(databaseName)
			.withSQLDatabaseEngine(SqlDatabaseEngine.MSSQL)
			.usingLoginName(loginName)
			.andLoginPassword(loginPassword)
		);
	}
	
	//constructor
	private MsSqlDatabaseAdapter(
		final String databaseName,
		final ISchema schema,
		final SqlConnectionPool pSQLConnectionPool
	) {
		
		super(
			databaseName,
			MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			schema,
			() ->
			MSSQLDatabaseAndSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
		
		mSQLConnectionPool = pSQLConnectionPool;
	}
	
	//method
	@Override
	public DatabaseAdapter getEmptyCopy() {
		return
		toIpOrAddress(getIpOrAddress())
		.andPort(getPort())
		.toDatabase(getDatabaseName())
		.usingLoginName(getLoginName())
		.andLoginPassword(getLoginPassword())
		.andSchema(getSchema());
	}
	
	//method
	private String getLoginPassword() {
		return mSQLConnectionPool.getLoginPassword();
	}
	
	//method
	private String getLoginName() {
		return mSQLConnectionPool.getLoginName();
	}
	
	//method
	private int getPort() {
		return mSQLConnectionPool.getPort();
	}
	
	//method
	private String getIpOrAddress() {
		return mSQLConnectionPool.getIpOrAddressName();
	}
}
