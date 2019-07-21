//package declaration
package ch.nolix.core.classProvider;

import ch.nolix.core.processProperties.WriteMode;
import ch.nolix.core.validator.Validator;

//class
public final class RegistrationMediator {
	
	//attribute
	private final ClassProvider parentClassProvider;
	
	//package-visible constructor
	RegistrationMediator(final ClassProvider parentClassProvider) {
		
		Validator.suppose(parentClassProvider).thatIsNamed("parent class provder").isNotNull();
		
		this.parentClassProvider = parentClassProvider;
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(final Class<I> interface_, final Class<C> class_) {		
		return parentClassProvider.register(interface_, class_);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		final WriteMode writeMode
	) {
		return parentClassProvider.register(interface_, class_, writeMode);
	}
}
