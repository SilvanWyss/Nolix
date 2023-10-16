//package declaration
package ch.nolix.core.provider.implprovider;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;
import ch.nolix.coreapi.providerapi.implproviderapi.IImplRegistratorMediator;

//class
public final class ImplRegistratorMediator<IN> implements IImplRegistratorMediator<IN> {

  //attribute
  private final ImplProvider implProvider;

  //attribute
  private final Class<IN> mInterface;

  //attribute
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
