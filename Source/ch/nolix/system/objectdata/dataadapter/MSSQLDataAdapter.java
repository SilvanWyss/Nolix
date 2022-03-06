//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.core.sql.SQLDatabaseEngine;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.system.objectschema.schemaadapter.MSSQLSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataandschemaadapter.MSSQLDataAndSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//static method
	public static MSSQLDataAdapterBuilder toIpOrAddress(final String ipOrAddressName) {
		return new MSSQLDataAdapterBuilder(ipOrAddressName);
	}
	
	//static method
	public static MSSQLDataAdapterBuilder toLocalHost() {
		return new MSSQLDataAdapterBuilder(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//constructor
	MSSQLDataAdapter(
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
	private MSSQLDataAdapter(
		final String databaseName,
		final ISchema<DataImplementation> schema,
		final SQLConnectionPool pSQLConnectionPool
	) {
		super(
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			schema,
			() ->
			MSSQLDataAndSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
	}
}
