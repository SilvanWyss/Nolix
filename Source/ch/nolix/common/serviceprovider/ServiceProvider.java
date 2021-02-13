//package declaration
package ch.nolix.common.serviceprovider;

//Java import
import java.util.HashMap;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.processproperty.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class ServiceProvider {
	
	//multi-attribute
	private final HashMap<Class<?>, Object> services = new HashMap<>();
	
	//method
	@SuppressWarnings("unchecked")
	public <S> S get(final Class<S> pInterface) {
		
		final var service = (S)services.get(pInterface);
		
		if (service == null) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain a service for the interface '"
				+ pInterface.getCanonicalName()
				+ "'."
			);
		}
		
		return service;
	}
	
	//method
	public <I, S extends I> void register(final Class<I> pInterface, final S service) {
		register(pInterface, service, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
	}
	
	//method
	public <I, S extends I> void register(final Class<I> pInterface, final S service, final WriteMode writeMode) {
		
		Validator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isNotNull();
		Validator.assertThat(service).thatIsNamed(LowerCaseCatalogue.SERVICE).isNotNull();
		
		switch (writeMode) {
			case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:
				
				if (services.putIfAbsent(pInterface, service) != null) {
					throw
					new InvalidArgumentException(
						this,
						"contains already a service with the given interface '"
						+ pInterface.getCanonicalName()
						+ "'"
					);
				}
				
				break;
			case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
				services.put(pInterface, service);
				break;
			case SKIP_WHEN_TARGET_EXISTS_ALREADY:
				services.putIfAbsent(pInterface, service);
				break;
		}
	}
}
