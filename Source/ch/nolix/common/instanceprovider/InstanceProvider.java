//package declaration
package ch.nolix.common.instanceprovider;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.processproperty.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class InstanceProvider {
	
	//multi-attribute
	private final HashMap<Class<?>, Class<?>> classes = new HashMap<>();
	
	//method
	public boolean containsClassFor(final Class<?> pInterface) {
		return classes.containsKey(pInterface);
	}
	
	//method
	//Important: The CoreClassProvider will found only the first (!) constructor with the given amount of parameters.
	@SuppressWarnings("unchecked")
	public <I, C extends I> C create(final Class<I> pInterface,	final Object[] arguments) {
		
		final var lClass = classes.get(pInterface);
		
		//Asserts that the given interface is registered.
		if (lClass == null) {
			throw new InvalidArgumentException(pInterface, "is not registered at the ClassProvider");
		}
		
		for (final var c : lClass.getConstructors()) {
			if (c.getParameterCount() == arguments.length) {
				c.setAccessible(true);
				try {
					return (C)c.newInstance(arguments);
				}
				catch (
					IllegalAccessException
					| IllegalArgumentException
					| InstantiationException
					| InvocationTargetException exception
				) {
					throw new WrapperException(exception);
				}
			}
		}
		
		throw new InvalidArgumentException(arguments);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(final Class<I> pInterface, final Class<C> pClass) {
		return register(pInterface, pClass, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(
		final Class<I> pInterface,
		final Class<C> pClass,
		final WriteMode writeMode
	) {
		
		Validator
		.assertThat(pInterface)
		.thatIsNamed(VariableNameCatalogue.INTERFACE)
		.isInterface();
		
		Validator
		.assertThat(pClass)
		.thatIsNamed(VariableNameCatalogue.CLASS)
		.isImplementing(pInterface);
		
		switch (writeMode) {
			case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:
				
				if (classes.putIfAbsent(pInterface, pClass) != null) {
					throw
					new InvalidArgumentException(
						this,
						"contains already a class that implements the interface '"
						+ pInterface.getCanonicalName()
						+ "'"
					);
				}
				
				break;
			case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
				classes.put(pInterface, pClass);
				break;
			case SKIP_WHEN_TARGET_EXISTS_ALREADY:
				classes.putIfAbsent(pInterface, pClass);
				break;
		}
		
		return new RegistrationMediator(this);
	}
}
