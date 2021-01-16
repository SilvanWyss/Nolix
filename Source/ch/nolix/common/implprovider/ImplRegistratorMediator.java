//package declaration
package ch.nolix.common.implprovider;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.implproviderapi.IImplRegistratorMediator;
import ch.nolix.common.processproperty.WriteMode;
import ch.nolix.common.validator.Validator;

//class
public final class ImplRegistratorMediator<IN> implements IImplRegistratorMediator<IN> {
	
	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	private final WriteMode writeMode;
	
	//visibility-reduced constructor
	ImplRegistratorMediator(final ImplProvider implProvider, final Class<IN> pInterface, WriteMode writeMode) {
		
		Validator.assertThat(implProvider).thatIsNamed(ImplProvider.class).isNotNull();
		Validator.assertThat(pInterface).thatIsNamed(VariableNameCatalogue.INTERFACE).isInterface();
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
