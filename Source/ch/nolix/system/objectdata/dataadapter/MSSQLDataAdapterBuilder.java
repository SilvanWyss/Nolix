//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.AndSchemaCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
final class MSSQLDataAdapterBuilder
extends
AndPortCapturer<
	ToDatabaseNameCapturer<
		UsingLoginNameCapturer<
			AndLoginPasswordCapturer<
				AndSchemaCapturer<
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
						new AndSchemaCapturer<>(null)
					)
				)
			)
		);
		
		setBuilder(() -> build(ipOrAddressName));
	}
	
	//method
	private MSSQLDataAdapter build(final String ipOrAddressName) {
		return		
		new MSSQLDataAdapter(
			ipOrAddressName,
			getPort(),
			next().getDatabaseName(),
			next().next().getLoginName(),
			next().next().next().getLoginPassword(),
			next().next().next().next().getRefSchema()
		);
	}
}
