//package declaration
package ch.nolix.core.sql;

import ch.nolix.core.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithSQLDatabaseEngineCapturer;

//class
public final class SQLConnectionPoolBuilder
extends
AndPortCapturer<
	AndDatabaseNameCapturer<
		WithSQLDatabaseEngineCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordCapturer<
					SqlConnectionPool
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
	private SqlConnectionPool build(final String ipOrAddressName) {
		return
		new SqlConnectionPool(
			ipOrAddressName,
			getPort(),
			next().getDatabaseName(),
			next().next().getSQLDatabaseEngine(),
			next().next().next().getLoginName(),
			next().next().next().next().getLoginPassword()
		);
	}
}
