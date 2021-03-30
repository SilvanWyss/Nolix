//package declaration
package ch.nolix.common.provider.implprovider;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.processproperty.WriteMode;
import ch.nolix.common.provider.implproviderapi.IImplRegistratorMediator;

//class
public final class ImplRegistratorMediator<IN> implements IImplRegistratorMediator<IN> {
	
	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	private final WriteMode writeMode;
	
	//constructor
	ImplRegistratorMediator(final ImplProvider implProvider, final Class<IN> pInterface, WriteMode writeMode) {
		
		Validator.assertThat(implProvider).thatIsNamed(ImplProvider.class).isNotNull();
		Validator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		Validator.assertThat(writeMode).thatIsNamed(WriteMode.class).isNotNull();
		
		this.implProvider = implProvider;
		mInterface = pInterface;
		this.writeMode = writeMode;
	}
	
	//method
	@Override
	public boolean containsImplementation() {
		return implProvider.containsImplementationFor(mInterface);
	}
	
	//method
	@Override
	public <IM extends IN> void registerImplementation(final Class<IM> implementation) {
		implProvider.registerImplementation(mInterface, implementation, writeMode);
	}
}
