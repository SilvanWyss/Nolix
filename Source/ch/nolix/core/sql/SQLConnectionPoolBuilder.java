//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.argumentcapturer.WithSQLDatabaseEngineCapturer;
import ch.nolix.core.builder.terminalargumentcapturer.AndLoginPasswordTerminalCapturer;

//class
public final class SQLConnectionPoolBuilder
extends
AndPortCapturer<
	AndDatabaseNameCapturer<
		WithSQLDatabaseEngineCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordTerminalCapturer<
					SQLConnectionPool
				>
			>
		>
	>
> {
	
	//constructor
	public SQLConnectionPoolBuilder(final String ipOrAddressName, final int defaultPort) {
		
		super(
			defaultPort,
			new AndDatabaseNameCapturer<>(
				new WithSQLDatabaseEngineCapturer<>(
					new UsingLoginNameCapturer<>(
						new AndLoginPasswordTerminalCapturer<>()
					)
				)
			)
		);
		
		setBuilder(() -> build(ipOrAddressName));
	}
	
	//method
	private SQLConnectionPool build(final String ipOrAddressName) {
		return
		new SQLConnectionPool(
			ipOrAddressName,
			getPort(),
			n().getDatabaseName(),
			n().n().getSQLDatabaseEngine(),
			n().n().n().getLoginName(),
			n().n().n().n().getLoginPassword()
		);
	}
}
