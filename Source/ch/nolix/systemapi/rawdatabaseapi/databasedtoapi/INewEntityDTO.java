//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databasedtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface INewEntityDTO {
	
	//method declaration
	IContainer<IContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
}
