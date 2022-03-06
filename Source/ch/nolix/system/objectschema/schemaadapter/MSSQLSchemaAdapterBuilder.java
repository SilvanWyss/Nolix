//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.terminalargumentcapturer.AndLoginPasswordTerminalCapturer;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public final class MSSQLSchemaAdapterBuilder
extends
AndPortCapturer<
	ToDatabaseNameCapturer<
		UsingLoginNameCapturer<
			AndLoginPasswordTerminalCapturer<MSSQLSchemaAdapter>
		>
	>
> {
	
	//constructor
	public MSSQLSchemaAdapterBuilder(final String ipOrAddressName, final int defaultPort) {
		
		super(
			defaultPort,
			new ToDatabaseNameCapturer<>(
				new UsingLoginNameCapturer<>(
					new AndLoginPasswordTerminalCapturer<>()
				)
			)
			
		);
		
		setBuilder(() -> build(ipOrAddressName));
	}
	
	//method
	private MSSQLSchemaAdapter build(final String ipOrAddressName) {
		return
		new MSSQLSchemaAdapter(
			n().getDatabaseName(),
			ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				n().getDatabaseName(),
				SQLConnectionPool
				.forIpOrAddressName(ipOrAddressName)
				.andPort(getPort())
				.andDatabase(n().getDatabaseName())
				.withSQLDatabaseEngine(SQLDatabaseEngine.MSSQL)
				.usingLoginName(n().n().getLoginName())
				.andLoginPassword(n().n().n().getLoginPassword())
			)
		);
	}
}
