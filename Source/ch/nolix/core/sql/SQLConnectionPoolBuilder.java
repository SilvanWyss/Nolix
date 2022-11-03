//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.argumentcapturer.WithSQLDatabaseEngineCapturer;

//class
public final class SQLConnectionPoolBuilder
extends
AndPortCapturer<
	AndDatabaseNameCapturer<
		WithSQLDatabaseEngineCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordCapturer<
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
						new AndLoginPasswordCapturer<>(null)
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
			next().getDatabaseName(),
			next().next().getSQLDatabaseEngine(),
			next().next().next().getLoginName(),
			next().next().next().next().getLoginPassword()
		);
	}
}
