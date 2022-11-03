//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
//own imports
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public final class MSSQLSchemaAdapterBuilder
extends
AndPortCapturer<
	ToDatabaseNameCapturer<
		UsingLoginNameCapturer<
			AndLoginPasswordCapturer<MSSQLSchemaAdapter>
		>
	>
> {
	
	//constructor
	public MSSQLSchemaAdapterBuilder(final String ipOrAddressName, final int defaultPort) {
		
		super(
			defaultPort,
			new ToDatabaseNameCapturer<>(
				new UsingLoginNameCapturer<>(
					new AndLoginPasswordCapturer<>(null)
				)
			)
		);
		
		setBuilder(() -> build(ipOrAddressName));
	}
	
	//method
	private MSSQLSchemaAdapter build(final String ipOrAddressName) {
		return
		new MSSQLSchemaAdapter(
			next().getDatabaseName(),
			ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				next().getDatabaseName(),
				SQLConnectionPool
				.forIpOrAddressName(ipOrAddressName)
				.andPort(getPort())
				.andDatabase(next().getDatabaseName())
				.withSQLDatabaseEngine(SQLDatabaseEngine.MSSQL)
				.usingLoginName(next().next().getLoginName())
				.andLoginPassword(next().next().next().getLoginPassword())
			)
		);
	}
}
