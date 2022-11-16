//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.system.objectdatabase.database.DataImplementation;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;

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
