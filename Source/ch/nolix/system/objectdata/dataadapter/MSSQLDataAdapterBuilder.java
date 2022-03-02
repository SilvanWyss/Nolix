//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.terminalargumentcapturer.AndSchemaTerminalCapturer;
import ch.nolix.core.constant.PortCatalogue;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
final class MSSQLDataAdapterBuilder
extends
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
> {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.MSSQL_PORT;
	
	//constructor
	public MSSQLDataAdapterBuilder(final String ipOrAddressName) {
		
		super(
			DEFAULT_PORT,
			new ToDatabaseNameCapturer<>(
				new UsingLoginNameCapturer<>(
					new AndLoginPasswordCapturer<>(
						new AndSchemaTerminalCapturer<>()
					)
				)
			)
		);
		
		setBuilder(() -> withIpOrAddressName(ipOrAddressName));
	}
	
	//method
	private MSSQLDataAdapter withIpOrAddressName(final String ipOrAddressName) {
		return		
		new MSSQLDataAdapter(
			ipOrAddressName,
			getPort(),
			n().getDatabaseName(),
			n().n().getLoginName(),
			n().n().n().getLoginPassword(),
			n().n().n().n().getRefSchema()
		);
	}
}
