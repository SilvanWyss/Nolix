//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ILoadedRecordDTO {
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
