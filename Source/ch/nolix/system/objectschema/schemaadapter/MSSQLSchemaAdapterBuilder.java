//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.core.sql.SqlDatabaseEngine;

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
			ch.nolix.system.sqldatabaserawschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				next().getDatabaseName(),
				SqlConnectionPool
				.forIpOrAddressName(ipOrAddressName)
				.andPort(getPort())
				.andDatabase(next().getDatabaseName())
				.withSQLDatabaseEngine(SqlDatabaseEngine.MSSQL)
				.usingLoginName(next().next().getLoginName())
				.andLoginPassword(next().next().next().getLoginPassword())
			)
		);
	}
}
