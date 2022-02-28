//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.argumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.argumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.argumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.argumentcapturer.WithIpOrAddressNameCapturer;
import ch.nolix.core.builder.base.Builder;
import ch.nolix.core.builder.terminalargumentcapturer.AndSchemaTerminalCapturer;
import ch.nolix.core.constant.PortCatalogue;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
final class MSSQLDataAdapterBuilder
extends
Builder<
	WithIpOrAddressNameCapturer<
		AndPortCapturer<
			ToDatabaseNameCapturer<
				UsingLoginNameCapturer<
					AndLoginPasswordCapturer<
						AndSchemaTerminalCapturer<
							ISchema<
								DataImplementation
							>,
							MSSQLDataAdapter
						>
					>
				>
			>
		>
	>,
	MSSQLDataAdapter
> {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.MSSQL_PORT;
	
	//method
	@Override
	protected MSSQLDataAdapter build(
		final
		WithIpOrAddressNameCapturer<
			AndPortCapturer<
				ToDatabaseNameCapturer<
					UsingLoginNameCapturer<
						AndLoginPasswordCapturer<
							AndSchemaTerminalCapturer<
								ISchema<
									DataImplementation
								>,
								MSSQLDataAdapter
							>
						>
					>
				>
			>
		>
		startArgumentCapturer
	) {
		return
		new MSSQLDataAdapter(
			startArgumentCapturer.getIpOrAddressName(),
			startArgumentCapturer.n().getPort(),
			startArgumentCapturer.n().n().getDatabaseName(),
			startArgumentCapturer.n().n().n().getLoginName(),
			startArgumentCapturer.n().n().n().n().getLoginPassword(),
			startArgumentCapturer.n().n().n().n().n().getRefSchema()
		);
	}
	
	//method
	@Override
	protected 
	WithIpOrAddressNameCapturer<
		AndPortCapturer<
			ToDatabaseNameCapturer<
				UsingLoginNameCapturer<
					AndLoginPasswordCapturer<
						AndSchemaTerminalCapturer<
							ISchema<
								DataImplementation
							>,
							MSSQLDataAdapter
						>
					>
				>
			>
		>
	>
	createStartArgumentCapturer() {
		return
		new WithIpOrAddressNameCapturer<>(
			new AndPortCapturer<>(
				DEFAULT_PORT,
				new ToDatabaseNameCapturer<>(
					new UsingLoginNameCapturer<>(
						new AndLoginPasswordCapturer<>(
							new AndSchemaTerminalCapturer<>()
						)
					)
				)
			)
		);
	}
}
