//package declaration
package ch.nolix.coreapi.providerapi.implproviderapi;

//interface
public interface IImplProviderMediator<IN> {

  // method declaration
  IN createInstance();

  // method declaration
  IN createInstance(final Object... arguments);
}
