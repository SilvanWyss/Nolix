//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databasedtoapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ILoadedEntityDTO {
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
