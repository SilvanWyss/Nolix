//package declaration
package ch.nolix.common.implprovider;

//Java import
import java.util.HashMap;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.implproviderapi.IImplProvider;
import ch.nolix.common.implproviderapi.IImplProviderMediator;
import ch.nolix.common.processproperty.WriteMode;

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
	
	//visibility-reduced method
	<IN> boolean containsImplementationFor(final Class<IN> pInterface) {
		return singleImplProviders.containsKey(pInterface);
	}
	
	//visibility-reduced method
	<IN, IM extends IN> void registerImplementation(
		final Class<IN> pInterface,
		final Class<IM> implementation,
		final WriteMode writeMode
	) {
		
		Validator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		Validator.assertThat(implementation).thatIsNamed("implementation").isImplementing(pInterface);
		
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
