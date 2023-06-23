//package declaration
package ch.nolix.core.sql;

import ch.nolix.core.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithSqlDatabaseEngineCapturer;

//class
public final class SqlConnectionPoolBuilder
extends
AndPortCapturer<
	AndDatabaseNameCapturer<
		WithSqlDatabaseEngineCapturer<
			UsingLoginNameCapturer<
				AndLoginPasswordCapturer<
					SqlConnectionPool
				>
			>
		>
	>
> {
	
	//constructor
	public SqlConnectionPoolBuilder(final String ipOrAddressName, final int defaultPort) {
		
		super(
			defaultPort,
			new AndDatabaseNameCapturer<>(
				new WithSqlDatabaseEngineCapturer<>(
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
