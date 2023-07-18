//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MsSqlDatabaseAdapterBuilder
extends
AndPortCapturer<
	ToDatabaseNameCapturer<
		UsingLoginNameCapturer<
			AndLoginPasswordCapturer<
				AndSchemaCapturer<
					ISchema,
					MsSqlDatabaseAdapter
				>
			>
		>
	>
> {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.MSSQL;
	
	//constructor
	public MsSqlDatabaseAdapterBuilder(final String ipOrDomain) {
		
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
		
		setBuilder(() -> build(ipOrDomain));
	}
	
	//method
	private MsSqlDatabaseAdapter build(final String ipOrDomain) {
		return		
		new MsSqlDatabaseAdapter(
			ipOrDomain,
			getPort(),
			next().getDatabaseName(),
			next().next().getLoginName(),
			next().next().next().getLoginPassword(),
			next().next().next().next().getStoredSchema()
		);
	}
}
