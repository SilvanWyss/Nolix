//package declaration
package ch.nolix.core.provider.implproviderapi;

//own imports
import ch.nolix.core.programcontrol.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<IN> extends IImplRegistratorMediator<IN> {
	
	//method declaration
	IImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode);
}
