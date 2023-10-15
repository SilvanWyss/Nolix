//package declaration
package ch.nolix.coreapi.providerapi.implproviderapi;

//interface
public interface IImplProvider {

  // method declaration
  <IN> IExtendedImplRegistratorMediator<IN> forInterface(Class<IN> pInterface);

  // method declaration
  <IN> IImplProviderMediator<IN> ofInterface(Class<IN> pInterface);
}
