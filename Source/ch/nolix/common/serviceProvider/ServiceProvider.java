//package declaration
package ch.nolix.common.serviceProvider;

//Java import
import java.util.HashMap;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.processProperties.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class ServiceProvider {
	
	//multi-attribute
	private final HashMap<Class<?>, Object> services = new HashMap<>();
	
	//method
	@SuppressWarnings("unchecked")
	public <S> S get(final Class<S> interface_) {
		
		final var service = (S)services.get(interface_);
		
		if (service == null) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain a service for the interface '"
				+ interface_.getCanonicalName()
				+ "'."
			);
		}
		
		return service;
	}
	
	//method
	public <I, S extends I> void register(
		final Class<I> interface_,
		final S service
	) {
		register(interface_, service, WriteMode.THROW_EXCEPTION_WHEN_EXISTS_ALREADY);
	}
	
	//method
	public <I, S extends I> void register(
		final Class<I> interface_,
		final S service,
		final WriteMode writeMode
	) {
		
		Validator
		.assertThat(interface_)
		.thatIsNamed(VariableNameCatalogue.INTERFACE)
		.isNotNull();
		
		Validator
		.assertThat(service)
		.thatIsNamed(VariableNameCatalogue.SERVICE)
		.isNotNull();
		
		switch (writeMode) {
			case THROW_EXCEPTION_WHEN_EXISTS_ALREADY:
				
				if (services.putIfAbsent(interface_, service) != null) {
					throw
					new InvalidArgumentException(
						this,
						"contains already a service with the given interface '"
						+ interface_.getCanonicalName()
						+ "'"
					);
				}
				
				break;
			case OVERWRITE_WHEN_EXISTS_ALREADY:
				services.put(interface_, service);
				break;
			case SKIP_WHEN_EXISTS_ALREADY:
				services.putIfAbsent(interface_, service);
				break;
		}
	}
}
