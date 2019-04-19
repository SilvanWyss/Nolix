//package declaration
package ch.nolix.core.classProvider;

//own import
import ch.nolix.core.enums.WriteMode;

//class
public final class RegistrationMediator {
	
	//package-visible constructor
	RegistrationMediator() {}
	
	//method
	public <I, C extends I> RegistrationMediator register(final Class<I> interface_, final Class<C> class_) {
		return CentralClassProvider.register(interface_, class_);
	}
	
	//method
	public <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		final WriteMode writeMode
	) {
		return CentralClassProvider.register(interface_, class_, writeMode);
	}
}
