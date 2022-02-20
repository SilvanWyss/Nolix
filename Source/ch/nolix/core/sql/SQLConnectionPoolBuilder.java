//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.WithIpOrAddressNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.WithSQLDatabaseEngineCapturer;
import ch.nolix.core.builder.base.Builder;
import ch.nolix.core.builder.terminalargumentcapturer.AndLoginPasswordTerminalCapturer;
import ch.nolix.core.constant.PortCatalogue;

//class
final class SQLConnectionPoolBuilder
extends
Builder<
	WithIpOrAddressNameCapturer<
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
		>
	>,
	SQLConnectionPool
> {
	
	//constructor
	@Override
	protected
	WithIpOrAddressNameCapturer<
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
		>
	>
	createStartArgumentCapturer() {
		return
		new WithIpOrAddressNameCapturer<>(
			new AndPortCapturer<>(PortCatalogue.MSSQL_PORT,
				new AndDatabaseNameCapturer<>(
					new WithSQLDatabaseEngineCapturer<>(
						new UsingLoginNameCapturer<>(
							new AndLoginPasswordTerminalCapturer<>()
						)
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected SQLConnectionPool build(
		final 
		WithIpOrAddressNameCapturer<
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
			>
		>
		startArgumentCapturer
	) {
		return
		new SQLConnectionPool(
			startArgumentCapturer.getIpOrAddressName(),
			startArgumentCapturer.n().getPort(),
			startArgumentCapturer.n().n().getDatabaseName(),
			startArgumentCapturer.n().n().n().getSQLDatabaseEngine(),
			startArgumentCapturer.n().n().n().n().getLoginName(),
			startArgumentCapturer.n().n().n().n().n().getLoginPassword()
		);
	}
}
