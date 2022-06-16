//package declaration
package ch.nolix.core.provider.implprovider;

//Java imports
import java.util.HashMap;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.processproperty.WriteMode;
import ch.nolix.core.provider.implproviderapi.IImplProvider;
import ch.nolix.core.provider.implproviderapi.IImplProviderMediator;

//class
public final class ImplProvider implements IImplProvider {
	
	//multi-attribute
	private final HashMap<Class<?>, SingleImplProvider<?>> singleImplProviders = new HashMap<>();
	
	@Override
	public <O> ExtendedImplRegistratorMediator<O> forInterface(final Class<O> pInterface) {
		return new ExtendedImplRegistratorMediator<>(this, pInterface);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <IN> IImplProviderMediator<IN> ofInterface(final Class<IN> pInterface) {
		
		final var singleImplProvider = singleImplProviders.get(pInterface);
		
		if (singleImplProvider == null) {
			throw new InvalidArgumentException(pInterface, "is not registered at the " + getName());
		}
		
		return (IImplProviderMediator<IN>)singleImplProvider;
	}
	
	//method
	<IN> boolean containsImplementationFor(final Class<IN> pInterface) {
		return singleImplProviders.containsKey(pInterface);
	}
	
	//method
	<IN, IM extends IN> void registerImplementation(
		final Class<IN> pInterface,
		final Class<IM> implementation,
		final WriteMode writeMode
	) {
		
		GlobalValidator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		GlobalValidator.assertThat(implementation).thatIsNamed("implementation").isImplementing(pInterface);
		
		switch (writeMode) {
			case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:
				
				if (singleImplProviders.putIfAbsent(pInterface, new SingleImplProvider<>(implementation)) != null) {
					throw new InvalidArgumentException(pInterface, "is already registered at the " + getName());
				}
				
				break;
			case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
				singleImplProviders.put(pInterface, new SingleImplProvider<>(implementation));
				break;
			case SKIP_WHEN_TARGET_EXISTS_ALREADY:
				singleImplProviders.putIfAbsent(pInterface, new SingleImplProvider<>(implementation));
				break;
		}
	}
	
	//method
	private String getName() {
		return getClass().getSimpleName();
	}
}
