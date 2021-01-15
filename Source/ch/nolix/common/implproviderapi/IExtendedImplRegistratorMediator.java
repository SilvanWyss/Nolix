//package declaration
package ch.nolix.common.implproviderapi;

//own import
import ch.nolix.common.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator<O> extends IImplRegistratorMediator<O> {
	
	//method declaration
	IImplRegistratorMediator<O> withWriteMode(WriteMode writeMode);
}
