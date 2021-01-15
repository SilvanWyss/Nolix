//package declaration
package ch.nolix.common.implproviderapi;

//own import
import ch.nolix.common.processproperty.WriteMode;

//interface
public interface IExtendedImplRegistratorMediator extends IImplRegistratorMediator {
	
	//method declaration
	IImplRegistratorMediator withWriteMode(WriteMode writeMode);
}
