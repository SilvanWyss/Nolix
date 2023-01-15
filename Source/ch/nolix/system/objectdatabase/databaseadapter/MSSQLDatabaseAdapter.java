//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

//own imports
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.core.sql.SQLDatabaseEngine;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;
import ch.nolix.system.objectdatabase.database.DataImplementation;
import ch.nolix.system.objectschema.schemaadapter.MSSQLSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataandschemaadapter.MSSQLDatabaseAndSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MSSQLDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MSSQLDatabaseAdapterBuilder toIpOrAddress(final String ipOrAddressName) {
		return new MSSQLDatabaseAdapterBuilder(ipOrAddressName);
	}
	
	//static method
	public static MSSQLDatabaseAdapterBuilder toLocalHost() {
		return new MSSQLDatabaseAdapterBuilder(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//attribute
	private final SQLConnectionPool mSQLConnectionPool; 
	
	//constructor
	MSSQLDatabaseAdapter(
		final String ipOrAddressName,
		final int port,
		final String databaseName,
		final String loginName,
		final String loginPassword,
		final ISchema<DataImplementation> schema
	) {
		this(
			databaseName,
			schema,
			SQLConnectionPool
			.forIpOrAddressName(ipOrAddressName)
			.andPort(port)
			.andDatabase(databaseName)
			.withSQLDatabaseEngine(SQLDatabaseEngine.MSSQL)
			.usingLoginName(loginName)
			.andLoginPassword(loginPassword)
		);
	}
	
	//constructor
	private MSSQLDatabaseAdapter(
		final String databaseName,
		final ISchema<DataImplementation> schema,
		final SQLConnectionPool pSQLConnectionPool
	) {
		
		super(
			databaseName,
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
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
