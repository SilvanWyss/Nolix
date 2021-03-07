//package declaration
package ch.nolix.common.implproviderapi;

import ch.nolix.common.programcontrol.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<IN> extends IImplRegistratorMediator<IN> {
	
	//method declaration
	IImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode);
}
