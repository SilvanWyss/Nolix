//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.terminalargumentcapturer.AndSchemaTerminalCapturer;
import ch.nolix.core.sql.MSSQLConnection;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.system.sqlrawdata.dataandschemaadapter.MSSQLDataAndSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//static method
	public static
	AndPortCapturer<
		ToDatabaseNameCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordCapturer<
					AndSchemaTerminalCapturer<
						ISchema<
							DataImplementation
						>,
						MSSQLDataAdapter
					>
				>
			>
		>
	>
	toIpOrAddress(final String ipOrAddressName) {
		return new MSSQLDataAdapterBuilder().getRefStart().withIpOrAddressName(ipOrAddressName);
	}
	
	//static method
	public static
	AndPortCapturer<
		ToDatabaseNameCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordCapturer<
					AndSchemaTerminalCapturer<
						ISchema<
							DataImplementation
						>,
						MSSQLDataAdapter
					>
				>
			>
		>
	>
	toLocalHost() {
		return new MSSQLDataAdapterBuilder().getRefStart().withLocalAddress();
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
		super(
			new MSSQLDataAndSchemaAdapter(
				databaseName,
				new MSSQLConnection(
					ipOrAddressName,
					port,
					databaseName,
					loginName,
					loginPassword
				)
			),
			schema
		);
	}
}
