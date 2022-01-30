//package declaration
package ch.nolix.core.provider.implprovider;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.processproperty.WriteMode;
import ch.nolix.core.provider.implproviderapi.IExtendedImplRegistratorMediator;

//class
public final class ExtendedImplRegistratorMediator<IN> implements IExtendedImplRegistratorMediator<IN> {

	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	
	//constructor
	ExtendedImplRegistratorMediator(final ImplProvider implProvider, final Class<IN> pInterface) {
		
		Validator.assertThat(implProvider).thatIsNamed(ImplProvider.class).isNotNull();
		Validator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		
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
