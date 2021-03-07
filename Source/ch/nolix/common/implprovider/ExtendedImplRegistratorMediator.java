//package declaration
package ch.nolix.common.implprovider;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.implproviderapi.IExtendedImplRegistratorMediator;
import ch.nolix.common.programcontrol.processproperty.WriteMode;

//class
public final class ExtendedImplRegistratorMediator<IN> implements IExtendedImplRegistratorMediator<IN> {

	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	
	//visibility-reduced constructor
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
