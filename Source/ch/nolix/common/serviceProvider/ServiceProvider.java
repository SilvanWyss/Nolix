//package declaration
package ch.nolix.common.serviceProvider;

//Java import
import java.util.HashMap;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
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
		register(interface_, service, false);
	}
	
	//method
	public <I, S extends I> void register(
		final Class<I> interface_,
		final S service,
		final boolean overwrite
	) {
		
		Validator
		.suppose(interface_)
		.thatIsNamed(VariableNameCatalogue.INTERFACE)
		.isNotNull();
		
		Validator
		.suppose(service)
		.thatIsNamed(VariableNameCatalogue.SERVICE)
		.isNotNull();
		
		if (!overwrite) {
			if (services.putIfAbsent(interface_, service) != null) {
				throw
				new InvalidArgumentException(
					this,
					"contains already a service with the given interface '"
					+ interface_.getCanonicalName()
					+ "'"
				);
			}
		}
		else {
			services.put(interface_, service);
		}
	}
}
