//package declaration
package ch.nolix.core.provider.implprovider;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.provider.implproviderapi.IExtendedImplRegistratorMediator;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
public final class ExtendedImplRegistratorMediator<IN> implements IExtendedImplRegistratorMediator<IN> {

	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	
	//constructor
	ExtendedImplRegistratorMediator(final ImplProvider implProvider, final Class<IN> pInterface) {
		
		GlobalValidator.assertThat(implProvider).thatIsNamed(ImplProvider.class).isNotNull();
		GlobalValidator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		
		this.implProvider = implProvider;
		mInterface = pInterface;
	}
	
	@Override
	public boolean containsImplementation() {
		return implProvider.containsImplementationFor(mInterface);
	}
	
	@Override
	public <IM extends IN> void registerImplementation(Class<IM> implementation) {
		implProvider.registerImplementation(
			mInterface,
			implementation,
			WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY
		);
	}
	
	@Override
	public ImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode) {
		return new ImplRegistratorMediator<>(implProvider, mInterface, writeMode);
	}
}
