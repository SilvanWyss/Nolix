//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.sql.SQLConnectionPool;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.MSSQL_PORT;
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return
		new MSSQLSchemaAdapter(
			databaseName,
			ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
	}
	
	//static method
	public static MSSQLSchemaAdapterBuilder toIpOrAddress(final String ipOrAddressName) {
		return new MSSQLSchemaAdapterBuilder(ipOrAddressName, DEFAULT_PORT);
	}
	
	//static method
	public static MSSQLSchemaAdapterBuilder toLocalhost() {
		return toIpOrAddress(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//constructor
	MSSQLSchemaAdapter(
		String databaseName,
		final ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(databaseName, pMSSQLSchemaAdapter);
	}
}
