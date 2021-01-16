//package declaration
package ch.nolix.common.implproviderapi;

//own import
import ch.nolix.common.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<IN> extends IImplRegistratorMediator<IN> {
	
	//method declaration
	IImplRegistratorMediator<IN> withWriteMode(WriteMode writeMode);
}
