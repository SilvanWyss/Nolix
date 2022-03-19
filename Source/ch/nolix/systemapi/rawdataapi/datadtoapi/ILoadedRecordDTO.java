//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//own imports
import ch.nolix.core.container.IContainer;

//interface
public interface ILoadedRecordDTO {
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
