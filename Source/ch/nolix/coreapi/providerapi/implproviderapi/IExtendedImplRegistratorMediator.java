//package declaration
package ch.nolix.coreapi.providerapi.implproviderapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<IN> extends IImplRegistratorMediator<IN> {

  //method declaration
  IImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode);
}
