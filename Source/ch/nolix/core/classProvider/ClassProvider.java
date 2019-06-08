//package declaration
package ch.nolix.core.classProvider;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.enums.WriteMode;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;

//class
public final class ClassProvider {
	
	//multi-attribute
	private final HashMap<Class<?>, Class<?>> classes = new HashMap<Class<?>, Class<?>>();
	
	//method
	public boolean containsClassFor(final Class<?> interface_) {
		return classes.containsKey(interface_);
	}
	
	//method
	//Important: The CoreClassProvider will found only the first (!) constructor with the given amount of parameters.
	@SuppressWarnings("unchecked")
	public <I, C extends I> C create(final Class<I> interface_,	final Object[] arguments) {
		
		final var class_ = classes.get(interface_);
		
		//Checks if the given interface is registered.
		if (class_ == null) {
			throw new InvalidArgumentException(interface_, "is not registered at the ClassProvider");
		}
		
		for (final var c : class_.getConstructors()) {
			if (c.getParameterCount() == arguments.length) {
				c.setAccessible(true);
				try {
					return (C)c.newInstance(arguments);
				} catch (
					IllegalAccessException
					| IllegalArgumentException
					| InstantiationException
					| InvocationTargetException exception
				) {
					throw new RuntimeException(exception);
				}
			}
		}
		
		throw new InvalidArgumentException(arguments);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(final Class<I> interface_, final Class<C> class_) {
		return register(interface_, class_, WriteMode.THROW_EXCEPTION_WHEN_EXISTS_ALREADY);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		final WriteMode writeMode
	) {
		
		Validator
		.suppose(interface_)
		.thatIsNamed(VariableNameCatalogue.INTERFACE)
		.isInterface();
		
		Validator
		.suppose(class_)
		.thatIsNamed(VariableNameCatalogue.CLASS)
		.isImplementing(interface_);
		
		switch (writeMode) {
			case THROW_EXCEPTION_WHEN_EXISTS_ALREADY:
				
				if (classes.putIfAbsent(interface_, class_) != null) {
					throw
					new InvalidArgumentException(
						this,
						"contains already a class that implements the interface '"
						+ interface_.getCanonicalName()
						+ "'"
					);
				}
				
				break;
			case OVERWRITE_WHEN_EXISTS_ALREADY:
				classes.put(interface_, class_);
				break;
			case SKIP_WHEN_EXISTS_ALREADY:
				classes.putIfAbsent(interface_, class_);
				break;
		}
		
		return new RegistrationMediator(this);
	}
}
