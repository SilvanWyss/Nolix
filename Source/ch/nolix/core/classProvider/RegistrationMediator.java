//package declaration
package ch.nolix.core.classProvider;

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
		boolean overwrite
	) {
		return CentralClassProvider.register(interface_, class_, overwrite);
	}
}
