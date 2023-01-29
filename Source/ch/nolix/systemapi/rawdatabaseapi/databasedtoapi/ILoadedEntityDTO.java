//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databasedtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ILoadedEntityDTO {
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
