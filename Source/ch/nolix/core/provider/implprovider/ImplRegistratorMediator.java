//package declaration
package ch.nolix.core.provider.implprovider;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.processproperty.WriteMode;
import ch.nolix.core.provider.implproviderapi.IImplRegistratorMediator;

//class
public final class ImplRegistratorMediator<IN> implements IImplRegistratorMediator<IN> {
	
	//attributes
	private final ImplProvider implProvider;
	private final Class<IN> mInterface;
	private final WriteMode writeMode;
	
	//constructor
	ImplRegistratorMediator(final ImplProvider implProvider, final Class<IN> pInterface, WriteMode writeMode) {
		
		GlobalValidator.assertThat(implProvider).thatIsNamed(ImplProvider.class).isNotNull();
		GlobalValidator.assertThat(pInterface).thatIsNamed(LowerCaseCatalogue.INTERFACE).isInterface();
		GlobalValidator.assertThat(writeMode).thatIsNamed(WriteMode.class).isNotNull();
		
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
