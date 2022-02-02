//package declaration
package ch.nolix.systemapi.rawobjectdataapi.datadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IRecordUpdateDTO {
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getUpdatedContentFields();
}
