//package declaration
package ch.nolix.common.instanceProvider;

//own imports
import ch.nolix.common.processProperties.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class RegistrationMediator {
	
	//attribute
	private final InstanceProvider parentClassProvider;
	
	//constructor
	RegistrationMediator(final InstanceProvider parentClassProvider) {
		
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
