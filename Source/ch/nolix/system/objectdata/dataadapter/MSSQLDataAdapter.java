//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.constant.IPv6Catalogue;
import ch.nolix.core.sql.MSSQLConnection;
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
		return new MSSQLDataAdapterBuilder(IPv6Catalogue.LOOP_BACK_ADDRESS);
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
		//TODO: Create a local SQLConnectionPool to serve the MSSQLSchemaAdapter and MSSQLDataAndSchemaAdapter.
		super(
			new MSSQLSchemaAdapter(
				databaseName,
				new MSSQLConnection(
					ipOrAddressName,
					port,
					loginName,
					loginPassword
				)
			),
			schema,
			() -> new MSSQLDataAndSchemaAdapter(
				databaseName,
				new MSSQLConnection(
					ipOrAddressName,
					port,
					loginName,
					loginPassword
				)
			)
		);
	}
}
