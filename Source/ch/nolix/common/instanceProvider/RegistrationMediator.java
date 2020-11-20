//package declaration
package ch.nolix.common.instanceProvider;

//own imports
import ch.nolix.common.processProperty.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class RegistrationMediator {
	
	//attribute
	private final InstanceProvider parentClassProvider;
	
	//constructor
	RegistrationMediator(final InstanceProvider parentClassProvider) {
		
		Validator.assertThat(parentClassProvider).thatIsNamed("parent class provder").isNotNull();
		
		this.parentClassProvider = parentClassProvider;
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(final Class<I> pInterface, final Class<C> pClass) {		
		return parentClassProvider.register(pInterface, pClass);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(
		final Class<I> pInterface,
		final Class<C> pClass,
		final WriteMode writeMode
	) {
		return parentClassProvider.register(pInterface, pClass, writeMode);
	}
}
