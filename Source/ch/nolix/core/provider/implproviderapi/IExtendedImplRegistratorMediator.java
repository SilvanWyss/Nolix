//package declaration
package ch.nolix.core.provider.implproviderapi;

import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<IN> extends IImplRegistratorMediator<IN> {
	
	//method declaration
	IImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode);
}
